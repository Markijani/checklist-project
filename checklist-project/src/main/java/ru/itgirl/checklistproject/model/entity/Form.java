package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "forms")
@Data
@NoArgsConstructor
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "group_num")
    private int groupNum;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "result")
    private int result;

    @Column(name = "level_1_answers")
    private Map<Question, Answer> level1Answers = new HashMap<>();

    @Column(name = "level_2_answers")
    private Map<Question, Answer> level2Answers = new HashMap<>();

    @Column(name = "level_3_answers")
    private Map<Question, Answer> level3Answers = new HashMap<>();

    public Form (String userName, int groupNum) {
        this.userName = userName;
        this.groupNum = groupNum;
        this.createdAt = LocalDateTime.now();
    }

    public void addLevel1Answer(String question, Integer answer) {
        level1Answers.put(question, answer);
    }

    public void addLevel2Answer(String question, Integer answer) {
        level2Answers.put(question, answer);
    }

    public void addLevel3Answer(String question, Integer answer) {
        level3Answers.put(question, answer);
    }
}
