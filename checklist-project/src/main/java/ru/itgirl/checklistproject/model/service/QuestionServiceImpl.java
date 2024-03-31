package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.*;
import ru.itgirl.checklistproject.model.entity.Answer;
import ru.itgirl.checklistproject.model.entity.Question;
import ru.itgirl.checklistproject.model.repository.LevelRepository;
import ru.itgirl.checklistproject.model.repository.QuestionRepository;
import ru.itgirl.checklistproject.model.repository.SuggestionRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SuggestionService suggestionService;
    private final LevelRepository levelRepository;
    private final SuggestionRepository suggestionRepository;
    private final AnswerService answerService;

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public QuestionDto getQuestionById(Long id) {
        return convertEntityToDto(questionRepository.findById(id).orElseThrow());
    }

    @Override
    public List<QuestionDto> getQuestionsByIncluded(boolean included) {
        List<Question> questions = questionRepository.findQuestionsByIncluded(included);
        return questions.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto createQuestion(QuestionCreateDto questionCreateDto) {
        Question question = questionRepository.save(convertDtoToEntity(questionCreateDto));
        Long questionId = question.getId();
        Set <Answer> answers = new HashSet<>();
        for (AnswerCreateDtoQuestions answer :
                questionCreateDto.getAnswers()) {
            answers.add(answerService.createAnswer(questionId, answer.getAnswerText(), answer.isCorrect()));
        }
        question.setAnswers(answers);
        return convertEntityToDto(questionRepository.save(question));
    }

    @Override
    public QuestionDto updateQuestion(QuestionUpdateDto questionUpdateDto) {
        Question question = questionRepository.findById(questionUpdateDto.getId()).orElseThrow();
        question.setText(questionUpdateDto.getText());
        question.setLevel(levelRepository.findLevelByName(questionUpdateDto.getLevel()));
        question.setIncluded(questionUpdateDto.isIncluded());
        Question savedQuestion = questionRepository.save(question);
        Set <Answer> answers = new HashSet<>();
        for (AnswerCreateDtoQuestions answer :
                questionUpdateDto.getAnswers()) {
            answers.add(answerService.createAnswer(questionUpdateDto.getId(), answer.getAnswerText(), answer.isCorrect()));
        }
        savedQuestion.setAnswers(answers);
        return convertEntityToDto(questionRepository.save(savedQuestion));
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Question convertDtoToEntity(QuestionCreateDto questionCreateDto) {
        return Question.builder()
                .text(questionCreateDto.getText())
                .included(questionCreateDto.getIncluded())
                .level(levelRepository.findLevelByName(questionCreateDto.getLevel()))
                .build();
    }

    private QuestionDto convertEntityToDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .level(question.getLevel().getName())
                .included(question.getIncluded())
                .text(question.getText())
                .answers(question.getAnswers().stream()
                        .map(answer -> AnswerDto.builder()
                                .answerText(answer.getText())
                                .correct(answer.isCorrect()).build()).collect(Collectors.toList()))
                .build();
    }
}
