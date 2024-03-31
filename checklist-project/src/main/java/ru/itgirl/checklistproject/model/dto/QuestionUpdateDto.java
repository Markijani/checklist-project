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
public class QuestionUpdateDto {
    private Long id;
    private String level;
    private boolean included;
    private String text;
    private List<SuggestionCreateDto> suggestions;
    private List<AnswerCreateDtoQuestions> answers;
}
