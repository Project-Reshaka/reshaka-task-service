package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tasks_input")
@SequenceGenerator(name = "tasks_input_seq", sequenceName = "tasks_input_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskInputEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_input_seq")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Long subjectId;

    @Column(columnDefinition = "jsonb")
    private String correctAnswers;

    private Boolean isHard;
    private Instant createdAt;
    private Instant updatedAt;
}
