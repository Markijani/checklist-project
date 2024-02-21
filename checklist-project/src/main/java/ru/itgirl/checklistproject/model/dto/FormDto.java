package ru.itgirl.checklistproject.model.dto;

import lombok.*;

import java.util.Map;

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
    private JuniorDto junior;
    private TraineeDto trainee;
}
