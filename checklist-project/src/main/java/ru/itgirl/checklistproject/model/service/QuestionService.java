package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllQuestions();

    List<QuestionDto> getQuestionsByIncluded(boolean included);
}
