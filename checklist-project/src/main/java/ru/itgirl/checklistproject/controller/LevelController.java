package ru.itgirl.checklistproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/level/{id}")
    LevelDto getLevelById(@PathVariable("id") Long id) {
        return levelService.getLevelById(id);
    }

    @PutMapping("level/update")
    LevelDto updateLevel(@RequestBody LevelUpdateDto levelUpdateDto) {
        return levelService.updateLevel(levelUpdateDto);
    }

}
