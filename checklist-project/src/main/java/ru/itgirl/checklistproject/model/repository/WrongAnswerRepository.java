package ru.itgirl.checklistproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itgirl.checklistproject.model.entity.WrongAnswer;

@Repository
public interface WrongAnswerRepository extends JpaRepository<WrongAnswer, Long> {
}
