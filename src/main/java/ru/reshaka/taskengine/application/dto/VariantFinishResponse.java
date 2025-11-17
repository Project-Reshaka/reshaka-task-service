package ru.reshaka.taskengine.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariantFinishResponse {
    private Long variantId;
    private Long userId;
    private int totalQuestions;
    private int correctAnswers;
    private long totalTimeSec;
}
