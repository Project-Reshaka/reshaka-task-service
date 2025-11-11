package ru.reshaka.taskengine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultTask {
    private UUID id;
    private UUID userId;
    private UUID taskId;
    private TaskBaseType taskType;
    private Object selectedAnswer;
    private Boolean isCorrect;
}
