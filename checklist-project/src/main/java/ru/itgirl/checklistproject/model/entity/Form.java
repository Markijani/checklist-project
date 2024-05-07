package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "form")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column
    private String token;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private Integer groupNum;

    @ManyToMany
    @JoinTable(name = "suggestion_form",
            joinColumns = @JoinColumn(name = "form_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = " suggestion_id", referencedColumnName = "id"))
    private Set<Suggestion> suggestions;

    @ManyToMany
    @JoinTable(name = "level_form",
            joinColumns = @JoinColumn(name = "form_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = " level_id", referencedColumnName = "id"))
    private Set<Level> levels;

    @OneToMany(mappedBy = "form")
    private List<WrongAnswer> wrongAnswers;
}
