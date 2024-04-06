package ru.itgirl.checklistproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.checklistproject.model.dto.LevelDto;
import ru.itgirl.checklistproject.model.dto.LevelUpdateDto;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.entity.Level;
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

    @PutMapping("level/update")
    LevelDto updateLevel(@RequestBody  LevelUpdateDto levelUpdateDto) {
        return levelService.updateLevel(levelUpdateDto);
    }

}
