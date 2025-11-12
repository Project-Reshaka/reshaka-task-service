package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")
@SequenceGenerator(name = "subjects_seq", sequenceName = "subjects_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subjects_seq")
    private Long id;

    private String name;
}
