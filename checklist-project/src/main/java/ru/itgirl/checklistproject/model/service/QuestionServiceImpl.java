package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.QuestionCreateDto;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.dto.SuggestionCreateDto;
import ru.itgirl.checklistproject.model.dto.SuggestionDto;
import ru.itgirl.checklistproject.model.entity.Question;
import ru.itgirl.checklistproject.model.entity.Suggestion;
import ru.itgirl.checklistproject.model.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SuggestionService suggestionService;

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
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
        for (SuggestionCreateDto suggestion:
                questionCreateDto.getSuggestions()) {
            suggestionService.createSuggestion(suggestion,questionId);
        }
        return convertEntityToDto(question);
    }

    public Question convertDtoToEntity(QuestionCreateDto questionCreateDto) {
                return Question.builder()
                .text(questionCreateDto.getText())
                .included(questionCreateDto.getIncluded())
                .level(questionCreateDto.getLevel())
                .build();
    }

    private QuestionDto convertEntityToDto(Question question) {
        List<SuggestionDto> suggestionDtoList = question.getTopics()
                .stream()
                .map(suggestion -> SuggestionDto.builder()
                        .id(suggestion.getId())
                        .link(suggestion.getLink())
                        .name(suggestion.getName())
                        .build()
                ).toList();
        return QuestionDto.builder()
                .id(question.getId())
                .level(question.getLevel().getName())
                .included(question.getIncluded())
                .suggestions(suggestionDtoList)
                .build();
    }
}
