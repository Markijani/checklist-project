package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.*;
import ru.itgirl.checklistproject.model.entity.Answer;
import ru.itgirl.checklistproject.model.entity.Form;
import ru.itgirl.checklistproject.model.entity.Level;
import ru.itgirl.checklistproject.model.entity.Suggestion;
import ru.itgirl.checklistproject.model.repository.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;
    private final AnswerRepository answerRepository;
    private final LevelRepository levelRepository;
    private final QuestionRepository questionRepository;
    private final SuggestionRepository suggestionRepository;

    @Override
    public FormDto createForm(FormCreateDto formCreateDto) {
        Set<Answer> answers = new HashSet<>();
        Set<Suggestion> suggestions = new HashSet<>();
        List<AnswerCreateDtoForms> answerDtos = formCreateDto.getAnswers();
        for (AnswerCreateDtoForms answerDto : answerDtos) {
            Answer answer = answerRepository.findByTextAndQuestion(answerDto.getAnswerText()
                    , questionRepository.findQuestionByText(answerDto.getQuestion()).orElseThrow()).orElseThrow();
            answers.add(answer);
        }
        for (Level level : levelRepository.findAll()) {
            List<Answer> answersLevel = answers.stream().filter(answer -> answer.getQuestion().getLevel().equals(level)).toList();
            if (!answersLevel.isEmpty()) {
                double correctAnswers = 0;
                for (Answer answer : answersLevel) {
                    if (answer.isCorrect()) {
                        correctAnswers++;
                    }
                }
                if (correctAnswers / answersLevel.size() <= 0.4) {
                    suggestions.addAll(level.getSuggestions());
                } else {
                    suggestions.add(suggestionRepository.findById(1L).orElseThrow());
                }
            }
        }
        Form form = Form.builder()
                .token(formCreateDto.getToken())
                .role(formCreateDto.getRole())
                .createdAt(LocalDateTime.now())
                .answers(answers)
                .suggestions(suggestions)
                .build();
        return convertEntityToDto(formRepository.save(form));
    }

    @Override
    public FormDto createFormAnswId(FormCreateDtoAnswId formCreateDto) {
        Set<Answer> answers = new HashSet<>();
        Set<Suggestion> suggestions = new HashSet<>();
        Set<Level> levels = new HashSet<>();
        List<Long> answersIds = formCreateDto.getAnswersId();
        for (Long answerId : answersIds) {
            Answer answer = answerRepository.findById(answerId).orElseThrow();
            answers.add(answer);
        }
        for (Level level : levelRepository.findAll()) {
            List<Answer> answersLevel = answers.stream().filter(answer -> answer.getQuestion().getLevel().equals(level)).toList();
            if (!answersLevel.isEmpty()) {
                levels.add(level);
                double correctAnswers = 0;
                for (Answer answer : answersLevel) {
                    if (answer.isCorrect()) {
                        correctAnswers++;
                    }
                }
                if (correctAnswers / answersLevel.size() <= 0.4) {
                    suggestions.addAll(level.getSuggestions());
                } else {
                    suggestions.add(suggestionRepository.findById(1L).orElseThrow());
                }
            }
        }
        Form form = Form.builder()
                .token(formCreateDto.getToken())
                .role(formCreateDto.getRole())
                .createdAt(LocalDateTime.now())
                .answers(answers)
                .suggestions(suggestions)
                .levels(levels)
                .build();
        return convertEntityToDto(formRepository.save(form));
    }

    @Override
    public FormDto updateForm(FormUpdateDto formUpdateDto) {
        Set<Answer> newAnswers = new HashSet<>();
        List<AnswerCreateDtoForms> answerDtos = formUpdateDto.getAnswers();
        for (AnswerCreateDtoForms answerDto : answerDtos) {
            newAnswers.add(answerRepository.findByTextAndQuestion(answerDto.getAnswerText()
                    , questionRepository.findQuestionByText(answerDto.getQuestion()).orElseThrow()).orElseThrow());
        }

        Form form = formRepository.findByToken(formUpdateDto.getToken()).orElseThrow();
        Set<Answer> oldAnswers = form.getAnswers();
        Set<Answer> allAnswers = new HashSet<>(newAnswers);
        allAnswers.addAll(oldAnswers);
        form.setAnswers(allAnswers);
        Set<Suggestion> suggestions = new HashSet<>();
        Set<Level> levels = new HashSet<>();
        for (Level level : levelRepository.findAll()) {
            List<Answer> answersLevel = allAnswers.stream().filter(answer -> answer.getQuestion().getLevel().equals(level)).toList();
            if (!answersLevel.isEmpty()) {
                levels.add(level);
                double correctAnswers = 0;
                for (Answer answer : answersLevel) {
                    if (answer.isCorrect()) {
                        correctAnswers++;
                    }
                }
                if (correctAnswers / answersLevel.size() <= 0.4) {
                    suggestions.addAll(level.getSuggestions());
                } else {
                    suggestions.add(suggestionRepository.findById(1L).orElseThrow());
                }
            }
        }
        form.setSuggestions(suggestions);
        form.setLevels(levels);
        return convertEntityToDto(formRepository.save(form));
    }

    @Override
    public FormDto updateFormAnswId(FormUpdateDtoAnswId formUpdateDto) {
        Set<Answer> newAnswers = new HashSet<>();
        List<Long> answersId = formUpdateDto.getAnswersId();
        for (Long answerID : answersId) {
            newAnswers.add(answerRepository.findById(answerID).orElseThrow());
        }
        Form form = formRepository.findByToken(formUpdateDto.getToken()).orElseThrow();
        form.setAnswers(newAnswers);
        Set<Suggestion> suggestions = new HashSet<>();
        Set<Level> levels = new HashSet<>();
        for (Level level : levelRepository.findAll()) {
            List<Answer> answersLevel = newAnswers.stream().filter(answer -> answer.getQuestion().getLevel().equals(level)).toList();
            if (!answersLevel.isEmpty()) {
                levels.add(level);
                double correctAnswers = 0;
                for (Answer answer : answersLevel) {
                    if (answer.isCorrect()) {
                        correctAnswers++;
                    }
                }
                if (correctAnswers / answersLevel.size() <= 0.4) {
                    suggestions.addAll(level.getSuggestions());
                } else {
                    suggestions.add(suggestionRepository.findById(1L).orElseThrow());
                }
            }
        }
        form.setSuggestions(suggestions);
        form.setLevels(levels);
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
        formRepository.deleteById(id);
    }

    private FormDto convertEntityToDto(Form form) {
        List<Answer> answers = answerRepository.findAnswerByFormsId(form.getId());
        List<Level> levels = levelRepository.findLevelByForms(form);
        List<LevelDtoForm> levelDtos = levels.stream().map(level ->
                LevelDtoForm.builder()
                        .name(level.getName())
                        .id(level.getId())
                        .build()).collect(Collectors.toList());
        List<AnswerDto> answerDtos = answers.stream().map(answer -> AnswerDto.builder()
                .id(answer.getId())
                .answerText(answer.getText())
                .question(answer.getQuestion().getText())
                .correct(answer.isCorrect())
                .build()).toList();
        return FormDto.builder()
                .token(form.getToken())
                .role(form.getRole())
                .createdAt(form.getCreatedAt().toString())
                .answers(answerDtos)
                .completedLevels(levelDtos)
                .suggestions(form.getSuggestions().stream().map(suggestion ->
                        SuggestionDto.builder()
                                .name(suggestion.getName())
                                .link(suggestion.getLink())
                                .build()).toList())
                .build();
    }
}
