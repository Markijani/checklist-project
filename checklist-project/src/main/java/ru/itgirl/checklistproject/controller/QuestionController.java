package ru.itgirl.checklistproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.checklistproject.model.dto.QuestionCreateDto;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.dto.QuestionUpdateDto;
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

    @PostMapping("question/create")
    QuestionDto createQuestion(@RequestBody QuestionCreateDto questionCreateDto) {
        return questionService.createQuestion(questionCreateDto);
    }

    @PutMapping ("question/update")
    QuestionDto updateQuestion (@RequestBody QuestionUpdateDto questionUpdateDto) {
        return questionService.updateQuestion(questionUpdateDto);
    }
    @DeleteMapping("/question/delete/{id}")
    void deleteBook(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
    }
}
