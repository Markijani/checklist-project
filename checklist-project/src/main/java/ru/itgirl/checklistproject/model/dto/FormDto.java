package ru.itgirl.checklistproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormDto {
    private Long id;
    private String username;
    private int groupNum;
    private String createdAt;
    private int result;
    private BeginnerDto beginner;
    private TraineeDto trainee;
    private JuniorDto junior;
}
