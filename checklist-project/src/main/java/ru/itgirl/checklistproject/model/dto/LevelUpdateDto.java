package ru.itgirl.checklistproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LevelUpdateDto {
    private Long id;
    private String name;
    private List<QuestionDto> questions;
    private SuggestionDto suggestion;
}
