package ru.reshaka.taskengine.domain.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class TaskInput extends TaskBase {
    private List<String> correctAnswers;

    @Override
    public ValidationResult validateAnswer(Object answer, Long userId) {
        if (!(answer instanceof String ans)) {
            return new ValidationResult(false);
        }
        boolean isCorrect = correctAnswers.stream()
                .anyMatch(a -> a.equalsIgnoreCase(ans.trim()));
        return new ValidationResult(isCorrect);
    }
}
