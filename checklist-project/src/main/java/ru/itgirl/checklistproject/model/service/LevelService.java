package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.LevelDto;
import ru.itgirl.checklistproject.model.dto.LevelUpdateDto;

import java.util.List;

public interface LevelService {
    List<LevelDto> getAllLevelsAndQuestions();

    LevelDto updateLevel(LevelUpdateDto updatedLevelDto);
}
