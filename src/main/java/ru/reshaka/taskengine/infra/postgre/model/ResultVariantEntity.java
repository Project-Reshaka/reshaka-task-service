package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "results_variants")
@SequenceGenerator(name = "results_variants_seq", sequenceName = "results_variants_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultVariantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "results_variants_seq")
    private Long id;

    private Long userId;
    private Long variantId;

    private Instant startedAt;
    private Instant finishedAt;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Integer timeTakenSec;
}
