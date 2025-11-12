package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "topics")
@SequenceGenerator(name = "topics_seq", sequenceName = "topics_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topics_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    private String name;
}
