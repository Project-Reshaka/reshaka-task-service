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
public class VariantTask {
    private Long variantId;
    private Long taskId;
    private TaskBaseType taskType;
    private Integer order;
    private Boolean isCorrect;
    private Object selectedAnswer;

}
