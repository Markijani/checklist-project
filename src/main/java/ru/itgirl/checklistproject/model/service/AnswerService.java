package ru.itgirl.checklistproject.model.service;

public interface AnswerService {
    void createAnswer(Long form_id, String question_name, int value);
}
