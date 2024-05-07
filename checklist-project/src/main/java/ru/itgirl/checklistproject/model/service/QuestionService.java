package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.QuestionDto;

public interface QuestionService {

    QuestionDto getQuestionById(Long id);

    void deleteQuestion(Long id);
}
