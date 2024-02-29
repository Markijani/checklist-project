package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.QuestionCreateDto;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.dto.SuggestionCreateDto;
import ru.itgirl.checklistproject.model.dto.SuggestionDto;
import ru.itgirl.checklistproject.model.entity.Question;
import ru.itgirl.checklistproject.model.entity.Suggestion;
import ru.itgirl.checklistproject.model.repository.LevelRepository;
import ru.itgirl.checklistproject.model.repository.QuestionRepository;
import ru.itgirl.checklistproject.model.repository.SuggestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SuggestionService suggestionService;
    private final LevelRepository levelRepository;
    private final SuggestionRepository suggestionRepository;

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
                .level(levelRepository.findLevelByName(questionCreateDto.getLevel()))
                .build();
    }

    private QuestionDto convertEntityToDto(Question question) {
        Long id = question.getId();
        List<SuggestionDto> suggestionDtoList = suggestionRepository.findByQuestionId(id)
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
                .text(question.getText())
                .build();
    }
}
