package ru.itgirl.checklistproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.checklistproject.model.dto.LevelDto;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.service.LevelService;
import ru.itgirl.checklistproject.model.service.QuestionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @GetMapping("/levelsAndQuestions")
    List<LevelDto> getQuestionsView() {
        return levelService.getAllLevelsAndQuestions();
    }
}
