package ru.reshaka.taskengine.domain.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class TaskChoice extends TaskBase {
    private List<Option> options;
    private List<Integer> correctAnswer;
    private TaskType type;

    @Override
    public ValidationResult validateAnswer(Object answer) {
        if (!(answer instanceof List<?> ansList)) {
            return new ValidationResult(false);
        }

        boolean isCorrect = correctAnswer.size() == ansList.size()
                && correctAnswer.containsAll(ansList);
        return new ValidationResult(isCorrect);
    }

    public enum TaskType { SINGLE, MULTIPLE }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Option {
        private int id;
        private String text;
    }
}
