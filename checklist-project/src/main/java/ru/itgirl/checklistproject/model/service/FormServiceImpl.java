package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itgirl.checklistproject.model.dto.*;
import ru.itgirl.checklistproject.model.entity.*;
import ru.itgirl.checklistproject.model.repository.*;

import java.time.LocalDateTime;
import java.util.*;
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
        Set<Level> completedLevels = form.getCompletedLevels();
        Set<Level> weakLevels = form.getWeakLevels();
        for (Level level : levelRepository.findAll()) {
            List<Answer> answersLevel = newAnswers.stream().filter(answer -> answer.getQuestion().getLevel().equals(level)).toList();
            if (!answersLevel.isEmpty()) {
                completedLevels.add(level);
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
                        wrongAnswer.setTopicId(answer.getQuestion().getLevel().getId());
                        wrongAnswerRepository.save(wrongAnswer);
                    }
                }
                if (correctAnswers / answersLevel.size() <= 0.4) {
                    weakLevels.add(level);
                }
            }
        }
        form.setWeakLevels(weakLevels);
        form.setCompletedLevels(completedLevels);
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

    @Transactional
    public void removeResult(Long id) {
        Form form = formRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Form with this id does not exist"));
        wrongAnswerRepository.deleteByForm(form);
        form.setCompletedLevels(null);
        form.setWeakLevels(null);
        formRepository.save(form);
    }

    private FormDto convertEntityToDtoAdmin(Form form) {
        List<LevelDtoForm> levelDtos = null;
        Set<LevelDtoForm> weakTopics = null;
        if (form.getCompletedLevels() != null) {
            levelDtos = form.getCompletedLevels().stream().map(level ->
                    LevelDtoForm.builder()
                            .name(level.getName())
                            .id(level.getId())
                            .build()).collect(Collectors.toList());
        }
        if (form.getWeakLevels() != null) {
            weakTopics = form.getWeakLevels().stream().map(level ->
                    LevelDtoForm.builder()
                            .name(level.getName())
                            .id(level.getId())
                            .build()).collect(Collectors.toSet());
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
        if (form.getCompletedLevels() != null) {
            levelDtos = form.getCompletedLevels().stream().map(level ->
                    LevelDtoForm.builder()
                            .name(level.getName())
                            .id(level.getId())
                            .build()).collect(Collectors.toList());
        }
        List<LinkDto> links = null;
        if (form.getCompletedLevels() != null) {
            suggestions = form.getCompletedLevels().stream().map(level ->
                    SuggestionDto.builder()
                            .title(level.getName())
                            .links(level.getSuggestions().stream().map(link -> LinkDto.builder()
                                    .link(link.getLink())
                                    .id(link.getId())
                                    .build()
                            ).collect(Collectors.toList()))
                            .wrongAnswers(form.getWrongAnswers().stream().filter(answer -> Objects.equals(answer.getTopicId(), level.getId()))
                                    .map(wrongAnswer ->
                                            WrongAnswerDto.builder()
                                                    .rightAnswer(wrongAnswer.getRightAnswer())
                                                    .userAnswer(wrongAnswer.getUserAnswer())
                                                    .question(wrongAnswer.getQuestion())
                                                    .build()).collect(Collectors.toList()))
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
                .suggestions(suggestions)
                .build();
    }
}
