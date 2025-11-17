package ru.reshaka.taskengine.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class TaskManual<T> extends TaskBase {

    private final ManualTaskValidator<T> validator;

    @Override
    public ValidationResult validateAnswer(Object answer, Long userId) {
        boolean res = validator.isCorrectAnswer(userId, (T) answer, this);
        return new ValidationResult(res);
    }

}
