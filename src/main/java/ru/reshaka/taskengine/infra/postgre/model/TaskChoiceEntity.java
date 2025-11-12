package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tasks_choice")
@SequenceGenerator(name = "tasks_choice_seq", sequenceName = "tasks_choice_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskChoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_choice_seq")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private String type; // 'single' | 'multiple'

    @Column(columnDefinition = "jsonb")
    private String options;

    @Column(columnDefinition = "jsonb")
    private String correctAnswer;

    private Boolean isHard;
    private Instant createdAt;
    private Instant updatedAt;
}
