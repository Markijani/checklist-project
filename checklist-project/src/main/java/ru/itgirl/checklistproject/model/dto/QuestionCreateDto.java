package ru.itgirl.checklistproject.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirl.checklistproject.model.entity.Level;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionCreateDto {
    private String text;
    private Boolean included;
    private Level level;
}
