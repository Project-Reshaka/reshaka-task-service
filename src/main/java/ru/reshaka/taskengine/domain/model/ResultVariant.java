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
public class ResultVariant {
    private UUID id;
    private UUID userId;
    private UUID variantId;
    private Instant endTime;
    private Instant startTime;
    private int totalQuestions;
    private int correctAnswers;
    private long totalTimeSec;
}
