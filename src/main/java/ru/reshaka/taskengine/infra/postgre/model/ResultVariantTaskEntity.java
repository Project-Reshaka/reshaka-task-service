package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "results_variant_tasks")
@SequenceGenerator(name = "results_variant_tasks_seq", sequenceName = "results_variant_tasks_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultVariantTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "results_variant_tasks_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_variant_id")
    private ResultVariantEntity resultVariant;

    private String taskType;
    private Long taskId;

    @Column(columnDefinition = "jsonb")
    private String selectedAnswer;

    private Boolean isCorrect;
    private Integer timeTakenSec;
}
