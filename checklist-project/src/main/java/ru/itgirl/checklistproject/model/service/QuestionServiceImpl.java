package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.entity.Question;
import ru.itgirl.checklistproject.model.repository.QuestionRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private QuestionDto convertEntityToDto (Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .level(question.getLevel().getName())
                .included(question.getIncluded())
                .build();
    }
}
