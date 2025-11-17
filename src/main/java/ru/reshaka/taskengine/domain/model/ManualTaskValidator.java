package ru.reshaka.taskengine.domain.model;

public interface ManualTaskValidator<T> {
    Boolean isCorrectAnswer(Long userId, T answer, TaskManual<T> task);
}
