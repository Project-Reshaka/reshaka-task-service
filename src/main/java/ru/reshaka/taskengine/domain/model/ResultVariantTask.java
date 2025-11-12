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
public class ResultVariantTask {
    private Long id;
    private Long userId;
    private Long resultVariantId;
    private Long taskId;
    private TaskBaseType taskType;
    private Object selectedAnswer;
    private Boolean isCorrect;
}
