package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
@Builder
@Getter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String text;

    @Column(nullable = false)
    private Boolean included;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "question")
    private Set<Suggestion> topics;

    @OneToMany(mappedBy = "answer")
    private Set<Answer> answers;
}
