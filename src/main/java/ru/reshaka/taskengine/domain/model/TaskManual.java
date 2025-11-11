package ru.reshaka.taskengine.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class TaskManual extends TaskBase {

    private final ManualTaskValidator validator;

    @Override
    public ValidationResult validateAnswer(Object answer) {
        return new ValidationResult(validator.isCorrectAnswer(answer, this));
    }
}
