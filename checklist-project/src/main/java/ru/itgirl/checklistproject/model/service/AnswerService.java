package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.entity.Answer;
import ru.itgirl.checklistproject.model.entity.Question;

public interface AnswerService {
    Answer createAnswer(Long questionId, String text, boolean correct);
}
