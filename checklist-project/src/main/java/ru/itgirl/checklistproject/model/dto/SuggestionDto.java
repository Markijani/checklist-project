package ru.itgirl.checklistproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirl.checklistproject.model.entity.Level;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionDto {
    private String name;
    private String link;
}
