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
public class FormDtoUser {
    private Long id;
    private String token;
    private String name;
    private String surname;
    private String email;
    private Integer groupNum;
    private List<LevelDtoForm> completedLevels;
    private List<SuggestionDto> suggestions;
    private List <WrongAnswerDto> wrongAnswers;
}
