package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "token")
    private String uid;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private Integer groupNum;

    @ManyToMany
    @JoinTable(name = "level_form",
            joinColumns = @JoinColumn(name = "form_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = " level_id", referencedColumnName = "id"))
    private Set<Level> completedLevels;

    @ManyToMany
    @JoinTable(name = "weak_level_form",
            joinColumns = @JoinColumn(name = "form_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = " level_id", referencedColumnName = "id"))
    private Set<Level> weakLevels;

    @OneToMany(mappedBy = "form",
            orphanRemoval=true)
    private List<WrongAnswer> wrongAnswers;
}
