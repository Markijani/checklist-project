package ru.itgirl.checklistproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.service.QuestionService;


@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/question/{id}")
    QuestionDto getQuestionById(@PathVariable("id") Long id) {
        return questionService.getQuestionById(id);
    }

    @DeleteMapping("/question/delete/{id}")
    void deleteBook(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
    }
}
