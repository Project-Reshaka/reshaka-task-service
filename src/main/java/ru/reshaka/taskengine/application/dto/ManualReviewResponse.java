package ru.reshaka.taskengine.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ManualReviewResponse {
    private Long taskId;
    private Long userId;
    private Boolean isCorrect;
    private String status;
}

