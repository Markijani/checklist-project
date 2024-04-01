package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.entity.Answer;

public interface AnswerService {
    Answer createAnswer(Long questionId, String text, boolean correct);
}
