package ru.itgirl.checklistproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.checklistproject.model.dto.FormDto;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.service.QuestionService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class QuestionController {

private final QuestionService questionService;

    @GetMapping("/questions")
    List<QuestionDto> getQuestionsView() {
        return questionService.getAllQuestions();
    }

    @GetMapping("questions/included")
    public List<QuestionDto> getQuestionsByIncluded(@RequestParam("included") boolean included) {
        return questionService.getQuestionsByIncluded(included);
    }
}
