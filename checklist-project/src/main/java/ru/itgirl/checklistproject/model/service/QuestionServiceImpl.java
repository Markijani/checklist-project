package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.AnswerDto;
import ru.itgirl.checklistproject.model.dto.QuestionDto;
import ru.itgirl.checklistproject.model.entity.Question;
import ru.itgirl.checklistproject.model.repository.QuestionRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public QuestionDto getQuestionById(Long id) {
        return convertEntityToDto(questionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("This question does not exist")));
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    private QuestionDto convertEntityToDto(Question question) {
        List<AnswerDto> answers = null;
        if (question.getAnswers() != null) {
            answers = question.getAnswers().stream()
                    .map(answer -> AnswerDto.builder()
                            .id(answer.getId())
                            .answerText(answer.getText())
                            .correct(answer.isCorrect()).build()).collect(Collectors.toList());
        }
        return QuestionDto.builder()
                .id(question.getId())
                .text(question.getText())
                .answers(answers)
                .build();
    }
}
