package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.entity.Answer;
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
    public void createAnswer(Long form_id, String question_name, int value) {
        Answer answer = Answer.builder()
                .form(formRepository.findById(form_id).orElseThrow())
                .question(questionRepository.findQuestionByName(question_name).orElseThrow())
                .value(value)
                .build();
        answerRepository.save(answer);
    }
}
