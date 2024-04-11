package ru.itgirl.checklistproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.checklistproject.model.dto.LevelDto;
import ru.itgirl.checklistproject.model.dto.LevelUpdateDto;
import ru.itgirl.checklistproject.model.service.LevelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @GetMapping("/levelsAndQuestions")
    List<LevelDto> getQuestionsView() {
        return levelService.getAllLevelsAndQuestions();
    }

    @PutMapping("level/update")
    LevelDto updateLevel(@RequestBody LevelUpdateDto levelUpdateDto) {
        return levelService.updateLevel(levelUpdateDto);
    }

}
