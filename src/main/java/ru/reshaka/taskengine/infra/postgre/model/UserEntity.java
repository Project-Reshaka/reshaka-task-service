package ru.reshaka.taskengine.infra.postgre.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    private Long id;

    private String username;
    private String passwordHash;
    private String role;

    private Instant createdAt;
    private Instant updatedAt;

}



