package ru.itgirl.checklistproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormCreateDto {
    private String username;
    private int groupNum;
    private BeginnerDto beginner;
    private JuniorDto junior;
    private TraineeDto trainee;
}
