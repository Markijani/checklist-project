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
public class FormDto {
    private String token;
    private String role;
    private String createdAt;
    private List<LevelDto> levels;
    private List<AnswerCreateDto> answers;
    private List<SuggestionDto> suggestions;
}
