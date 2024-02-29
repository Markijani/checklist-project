package ru.itgirl.checklistproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionCreateDto {

    private String text;
    private Boolean included;
    private String level;
    private List<SuggestionCreateDto> suggestions;
}
