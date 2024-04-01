package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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
    private List<Question> questions;

    @OneToMany(mappedBy = "level")
    private List<Suggestion> suggestions;

    @ManyToMany(mappedBy = "levels")
    private Set<Form> forms;
}
