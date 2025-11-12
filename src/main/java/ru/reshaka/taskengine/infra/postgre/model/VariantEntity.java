package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "variants")
@SequenceGenerator(name = "variants_seq", sequenceName = "variants_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variants_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserEntity author;

    private String name;
    private String description;
    private Boolean isTimed;
    private Integer timeLimitSec;
    private Instant createdAt;
    private Instant updatedAt;
}
