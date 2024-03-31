package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.entity.Answer;
import ru.itgirl.checklistproject.model.entity.Question;
import ru.itgirl.checklistproject.model.repository.AnswerRepository;
import ru.itgirl.checklistproject.model.repository.FormRepository;
import ru.itgirl.checklistproject.model.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final FormRepository formRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Answer createAnswer(Long questionId, String answerText, boolean correct) {
        Answer answer = Answer.builder()
                .question(questionRepository.findById(questionId).orElseThrow())
                .text(answerText)
                .correct(correct)
                .build();
       return answerRepository.save(answer);
    }
}
