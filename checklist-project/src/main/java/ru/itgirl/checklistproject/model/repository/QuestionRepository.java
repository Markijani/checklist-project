package ru.itgirl.checklistproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itgirl.checklistproject.model.entity.Question;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findQuestionByText(String name);
}
