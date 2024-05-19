package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.*;
import ru.itgirl.checklistproject.model.entity.*;
import ru.itgirl.checklistproject.model.repository.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;
    private final AnswerRepository answerRepository;
    private final LevelRepository levelRepository;
    private final QuestionRepository questionRepository;
    private final WrongAnswerRepository wrongAnswerRepository;

    @Override
    public FormDto createForm(FormCreateDto formCreateDto) {
        Form form = Form.builder()
                .uid(formCreateDto.getUid())
                .createdAt(LocalDateTime.now())
                .name(formCreateDto.getName())
                .surname(formCreateDto.getSurname())
                .email(formCreateDto.getEmail())
                .groupNum(formCreateDto.getGroupNum())
                .build();
        return convertEntityToDtoAdmin(formRepository.save(form));
    }

    @Override
    public FormDto updateFormAnswId(FormUpdateDtoAnswId formUpdateDto) {
        Set<Answer> newAnswers = new HashSet<>();
        List<Long> answersId = formUpdateDto.getAnswersId();
        for (Long answerID : answersId) {
            newAnswers.add(answerRepository.findById(answerID).orElseThrow(() -> new NoSuchElementException("This answer does not exist")));
        }
        Form form = formRepository.findByUid(formUpdateDto.getUid()).orElseThrow(() -> new NoSuchElementException("Form with this uid does not exist"));
        Set<Suggestion> suggestions = form.getSuggestions();
        Set<Level> levels = form.getLevels();
        for (Level level : levelRepository.findAll()) {
            List<Answer> answersLevel = newAnswers.stream().filter(answer -> answer.getQuestion().getLevel().equals(level)).toList();
            if (!answersLevel.isEmpty()) {
                levels.add(level);
                double correctAnswers = 0;
                for (Answer answer : answersLevel) {
                    if (answer.isCorrect()) {
                        correctAnswers++;
                    } else {
                        WrongAnswer wrongAnswer = new WrongAnswer();
                        Question question = answer.getQuestion();
                        String correctAnswer = question.getAnswers().stream().filter(Answer::isCorrect).findAny().orElseThrow(() -> new NoSuchElementException("Correct answer does not exist")).getText();
                        wrongAnswer.setForm(form);
                        wrongAnswer.setRightAnswer(correctAnswer);
                        wrongAnswer.setUserAnswer(answer.getText());
                        wrongAnswer.setQuestion(question.getText());
                        wrongAnswer.setTopic(answer.getQuestion().getLevel().getName());
                        wrongAnswerRepository.save(wrongAnswer);
                    }
                }
                if (correctAnswers / answersLevel.size() <= 0.4) {
                    suggestions.addAll(level.getSuggestions());
                }
            }
        }
        form.setSuggestions(suggestions);
        form.setLevels(levels);
        return convertEntityToDtoAdmin(formRepository.save(form));
    }

    @Override
    public List<FormDto> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream().map(this::convertEntityToDtoAdmin).collect(Collectors.toList());
    }

    @Override
    public FormDto getFormById(Long id) {
        return convertEntityToDtoAdmin(formRepository.findById(id).orElseThrow(() -> new NoSuchElementException("This form does not exist")));
    }

    @Override
    public FormDto getFormByUid(String uid) {
        Form form = formRepository.findByUid(uid).orElseThrow(() -> new NoSuchElementException("Form with this uid does not exist"));
        return convertEntityToDtoAdmin(form);
    }

    @Override
    public FormDtoUser getFormByUidUser(String uid) {
        Form form = formRepository.findByUid(uid).orElseThrow(() -> new NoSuchElementException("Form with this uid does not exist"));
        return convertEntityToDtoUser(form);
    }

    @Override
    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }

    private FormDto convertEntityToDtoAdmin(Form form) {
        List<LevelDtoForm> levelDtos = null;
        Set<String> weakTopics = null;
        if (form.getLevels() != null) {
            levelDtos = form.getLevels().stream().map(level ->
                    LevelDtoForm.builder()
                            .name(level.getName())
                            .id(level.getId())
                            .build()).collect(Collectors.toList());
        }
        if (form.getSuggestions() != null) {
            weakTopics = form.getSuggestions().stream().map(suggestion ->
                    suggestion.getLevel().getName()).collect(Collectors.toSet());
        }
        return FormDto.builder()
                .id(form.getId())
                .uid(form.getUid())
                .name(form.getName())
                .surname(form.getSurname())
                .email(form.getEmail())
                .groupNum(form.getGroupNum())
                .createdAt(form.getCreatedAt().toString())
                .completedLevels(levelDtos)
                .weakTopics(weakTopics)
                .build();
    }

    private FormDtoUser convertEntityToDtoUser(Form form) {
        List<LevelDtoForm> levelDtos = null;
        List<SuggestionDto> suggestions = null;
        List<WrongAnswerDto> wrongAnswers = null;
        if (form.getLevels() != null) {
            levelDtos = form.getLevels().stream().map(level ->
                    LevelDtoForm.builder()
                            .name(level.getName())
                            .id(level.getId())
                            .build()).collect(Collectors.toList());
        }
        if (form.getWrongAnswers() != null) {
            wrongAnswers = form.getWrongAnswers().stream().map(wrongAnswer ->
                    WrongAnswerDto.builder()
                            .rightAnswer(wrongAnswer.getRightAnswer())
                            .topic(wrongAnswer.getTopic())
                            .userAnswer(wrongAnswer.getUserAnswer())
                            .question(wrongAnswer.getQuestion())
                            .build()
            ).collect(Collectors.toList());
        }
        if (form.getSuggestions() != null) {
            suggestions = form.getSuggestions().stream().map(suggestion ->
                    SuggestionDto.builder()
                            .link(suggestion.getLink())
                            .name(suggestion.getName())
                            .build()).collect(Collectors.toList());
        }
        return FormDtoUser.builder()
                .id(form.getId())
                .uid(form.getUid())
                .name(form.getName())
                .surname(form.getSurname())
                .email(form.getEmail())
                .groupNum(form.getGroupNum())
                .completedLevels(levelDtos)
                .wrongAnswers(wrongAnswers)
                .suggestions(suggestions)
                .build();
    }
}
