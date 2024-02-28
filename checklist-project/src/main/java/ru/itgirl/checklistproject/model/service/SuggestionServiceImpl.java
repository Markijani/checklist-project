package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.SuggestionCreateDto;
import ru.itgirl.checklistproject.model.dto.SuggestionDto;
import ru.itgirl.checklistproject.model.entity.Suggestion;
import ru.itgirl.checklistproject.model.repository.QuestionRepository;
import ru.itgirl.checklistproject.model.repository.SuggestionRepository;

@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService{
    private final SuggestionRepository suggestionRepository;
    private final QuestionRepository questionRepository;

    public void createSuggestion (SuggestionCreateDto suggestionCreateDto, Long questionId) {
        Suggestion newSuggestion = Suggestion.builder()
                .question(questionRepository.findById(questionId).orElseThrow())
                .link(suggestionCreateDto.getLink())
                .name(suggestionCreateDto.getName())
                .build();
        Suggestion savedSuggestion = suggestionRepository.save(newSuggestion);
        SuggestionDto.builder()
                .id(savedSuggestion.getId())
                .name(savedSuggestion.getLink())
                .link(savedSuggestion.getLink())
                .build();
    }
}
