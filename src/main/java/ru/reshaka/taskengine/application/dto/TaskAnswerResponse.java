package ru.reshaka.taskengine.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskAnswerResponse {
    private Long taskId;
    private boolean isCorrect;
    private Object selectedAnswer;
}
