package ru.itgirl.checklistproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormDto {
    private Long id;
    private String uid;
    private String name;
    private String surname;
    private String email;
    private Integer groupNum;
    private String createdAt;
    private List<LevelDtoForm> completedLevels;
    private Set<LevelDtoForm> weakTopics;
}
