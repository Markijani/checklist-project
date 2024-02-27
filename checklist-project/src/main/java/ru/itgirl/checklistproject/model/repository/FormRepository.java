package ru.itgirl.checklistproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itgirl.checklistproject.model.entity.Form;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM form WHERE groupnum = ?")
    Optional<List<Form>> findFormsByGroupBySql(String groupNum);

    @Query(nativeQuery = true, value = "SELECT * FROM form WHERE groupnum = :groupNum AND username = :username")
    Optional<List<Form>> findFormsByGroupAndNameBySql(@Param("groupNum") String groupNum, @Param("username") String username);
}