package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tasks_manual")
@SequenceGenerator(name = "tasks_manual_seq", sequenceName = "tasks_manual_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskManualEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_manual_seq")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Boolean isHard;
    private Instant createdAt;
    private Instant updatedAt;
}
