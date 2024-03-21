package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Column(name = "username")
    private String userName;

    @Column(name = "groupnum")
    private String groupNum;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column
    private int result;

    @ManyToMany
    @JoinTable(name = "answer_form",
            joinColumns=  @JoinColumn(name="form_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name=" answer_id", referencedColumnName="id") )
    private Set<Answer> answers;
}
