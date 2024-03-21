package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "answer_form",
            joinColumns=  @JoinColumn(name="answer_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name=" form_id", referencedColumnName="id") )
    private Form form;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    @Setter
    private boolean correct;

    @Column(nullable = false)
    @Setter
    private String text;
}
