package ru.itgirl.checklistproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirl.checklistproject.model.entity.Question;
import ru.itgirl.checklistproject.model.entity.Suggestion;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LevelUpdateDto {
    private Long id;
    private String name;
    private List<Question> questions;

    private List<Suggestion> suggestions;
}
