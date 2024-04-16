package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
@Builder
@Getter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @Setter
    private Question question;

    @Column(nullable = false)
    @Setter
    private boolean correct;

    @Column(nullable = false)
    @Setter
    private String text;
}
