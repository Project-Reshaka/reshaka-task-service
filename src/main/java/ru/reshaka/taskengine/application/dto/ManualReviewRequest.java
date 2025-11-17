package ru.reshaka.taskengine.application.dto;

import lombok.Data;

@Data
public class ManualReviewRequest {
    private Long taskId;
    private Boolean isCorrect;
}

