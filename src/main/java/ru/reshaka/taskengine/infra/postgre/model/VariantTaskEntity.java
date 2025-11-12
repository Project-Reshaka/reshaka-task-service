package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "variant_tasks")
@SequenceGenerator(name = "variant_tasks_seq", sequenceName = "variant_tasks_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variant_tasks_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id")
    private VariantEntity variant;

    private String taskType;
    private Long taskId;
    private Integer orderNum;
}
