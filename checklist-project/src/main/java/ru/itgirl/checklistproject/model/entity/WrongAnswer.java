package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WrongAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long topicId;

    @Column
    private String question;

    @Column
    private String userAnswer;

    @Column
    private String rightAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private Form form;
}
