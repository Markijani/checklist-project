package ru.itgirl.checklistproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itgirl.checklistproject.model.entity.Answer;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    // дописать Query
    List<Answer> findAnswerByForm_id(Long id);
}
