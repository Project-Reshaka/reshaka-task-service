package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "results_tasks")
@SequenceGenerator(name = "results_tasks_seq", sequenceName = "results_tasks_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "results_tasks_seq")
    private Long id;

    private Long userId;
    private String taskType;
    private Long taskId;
    private Instant answeredAt;

    @Column(columnDefinition = "jsonb")
    private String selectedAnswer;

    private Boolean isCorrect;
    private Integer timeTakenSec;
}
