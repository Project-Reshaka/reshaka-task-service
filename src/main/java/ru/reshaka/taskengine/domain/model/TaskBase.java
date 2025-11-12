package ru.reshaka.taskengine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public abstract class TaskBase {
    private Long id;
    private String text;
    private boolean isHard;
    private Instant createdAt;
    private Instant updatedAt;

    public abstract ValidationResult validateAnswer(Object answer);
}
