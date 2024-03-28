package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.AnswerCreateDto;
import ru.itgirl.checklistproject.model.dto.FormCreateDto;
import ru.itgirl.checklistproject.model.dto.FormDto;
import ru.itgirl.checklistproject.model.dto.LevelDto;
import ru.itgirl.checklistproject.model.entity.Answer;
import ru.itgirl.checklistproject.model.entity.Form;
import ru.itgirl.checklistproject.model.entity.Level;
import ru.itgirl.checklistproject.model.repository.AnswerRepository;
import ru.itgirl.checklistproject.model.repository.FormRepository;
import ru.itgirl.checklistproject.model.repository.LevelRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;
    private final AnswerService answerService;
    private final AnswerRepository answerRepository;
    private final LevelRepository levelRepository;

    @Override
    public FormDto createForm(FormCreateDto formCreateDto) {
        Set<Answer> answers = new HashSet<>();
        List<AnswerCreateDto> answerDtos = formCreateDto.getAnswers();
        for (AnswerCreateDto answerDto : answerDtos) {
            answers.add(answerRepository.findByQuestionText(answerDto.getQuestion()).orElseThrow());
        }
        Form form = Form.builder()
                .token(formCreateDto.getToken())
                .role(formCreateDto.getRole())
                .createdAt(LocalDateTime.now())
                .answers(answers)
                .build();
        return convertEntityToDto(formRepository.save(form));
    }

    @Override
    public List<FormDto> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public FormDto getFormById(Long id) {
        return convertEntityToDto(formRepository.findById(id).orElseThrow());
    }

    @Override
    public FormDto getFormByToken(String token) {
        Form form = formRepository.findByToken(token).orElseThrow();
        return convertEntityToDto(form);
    }

    @Override
    public void deleteForm(Long id) {
        answerRepository.deleteAll(answerRepository.findAnswerByFormsId(id));
        formRepository.deleteById(id);
    }

    private FormDto convertEntityToDto(Form form) {
        List<Answer> answers = answerRepository.findAnswerByFormsId(form.getId());
        List<Level> levels = levelRepository.findAll();
        List<LevelDto> levelDtos = levels.stream().map(level ->
                LevelDto.builder()
                        .name(level.getName())
                        .completed(false)
                        .build()).collect(Collectors.toList());
        for (Answer answer : answers) {
            Level completedLevel = answer.getQuestion().getLevel();
            Optional<LevelDto> completedLevelDto = levelDtos.stream()
                    .filter(level -> level.getName().equals(completedLevel.getName()))
                    .findAny();
            if (completedLevelDto.isPresent()) {
                LevelDto levelDto = completedLevelDto.get();
                levelDto.setCompleted(true);
                levelDtos.set(levelDtos.indexOf(levelDto), levelDto);
            }
        }
        List<AnswerCreateDto> answerDtos = answers.stream().map(answer -> AnswerCreateDto.builder()
                .answerText(answer.getText())
                .question(answer.getQuestion().getText())
                .build()).toList();
        return FormDto.builder()
                .token(form.getToken())
                .role(form.getRole())
                .createdAt(form.getCreatedAt().toString())
                .answers(answerDtos)
                .levels(levelDtos)
                .build();
    }
}
