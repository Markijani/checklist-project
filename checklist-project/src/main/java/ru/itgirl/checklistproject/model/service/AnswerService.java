package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.entity.Question;

public interface AnswerService {
    void createAnswer(Question question, String text, boolean correct);
}
