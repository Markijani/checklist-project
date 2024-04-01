package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.AnswerDto;
import ru.itgirl.checklistproject.model.dto.LevelDto;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.entity.Level;
import ru.itgirl.checklistproject.model.repository.LevelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;

    @Override
    public List<LevelDto> getAllLevelsAndQuestions() {
        List<Level> levels = levelRepository.findAll();
        return levels.stream().map(level -> LevelDto.builder()
                .id(level.getId())
                .name(level.getName())
                .questions(level.getQuestions().stream().map(question -> QuestionDto.builder()
                        .id(question.getId())
                        .text(question.getText())
                        .included(question.getIncluded())
                        .answers(question.getAnswers().stream().map(answer -> AnswerDto.builder()
                                .id(answer.getId()).answerText(answer.getText()).correct(answer.isCorrect())
                                .build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
    }
}
