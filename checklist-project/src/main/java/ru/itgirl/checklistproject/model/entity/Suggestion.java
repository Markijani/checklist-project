package ru.itgirl.checklistproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suggestion")
@Builder
@Getter
@Entity
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Setter
    private String name;

    @Column
    @Setter
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    @Setter
    private Level level;
}
