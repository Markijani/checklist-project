package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.entity.Answer;

public interface AnswerService {
    Answer createAnswer(Long form_id, String question_name, int value);
}
