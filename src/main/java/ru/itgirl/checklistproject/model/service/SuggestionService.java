package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.SuggestionCreateDto;

public interface SuggestionService {

    void createSuggestion(SuggestionCreateDto suggestionCreateDto, Long questionId);
}
