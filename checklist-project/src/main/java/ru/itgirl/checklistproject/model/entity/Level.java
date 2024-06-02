package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "level")
@Builder
@Getter
@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String name;

    @OneToMany(mappedBy = "level")
    @Setter
    private Set<Question> questions;

    @OneToMany(mappedBy = "level")
    @Setter
    private Set<Suggestion> suggestions;

    @ManyToMany(mappedBy = "completedLevels")
    private Set<Form> forms;
}
