package ru.itgirl.checklistproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itgirl.checklistproject.model.entity.Form;
import ru.itgirl.checklistproject.model.entity.Level;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    Level findLevelByName(String name);

    List<Level> findLevelByForms(Form form);
}
