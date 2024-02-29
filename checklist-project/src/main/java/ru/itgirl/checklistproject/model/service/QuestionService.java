package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.QuestionCreateDto;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.dto.QuestionUpdateDto;

import java.util.List;

public interface QuestionService {

    QuestionDto createQuestion(QuestionCreateDto questionCreateDto);

    List<QuestionDto> getAllQuestions();

    List<QuestionDto> getQuestionsByIncluded(boolean included);

    QuestionDto updateQuestion(QuestionUpdateDto questionUpdateDto);

    void deleteQuestion(Long id);
}
