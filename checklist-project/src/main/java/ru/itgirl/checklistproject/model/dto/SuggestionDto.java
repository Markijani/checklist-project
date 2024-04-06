package ru.itgirl.checklistproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirl.checklistproject.model.entity.Level;
import ru.itgirl.checklistproject.model.entity.Question;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionDto {
    private Level level;
    private String name;
    private String link;
    private List<Question> questions;
}
