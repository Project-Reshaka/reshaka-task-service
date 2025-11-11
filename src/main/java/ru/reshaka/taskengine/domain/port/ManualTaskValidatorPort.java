package ru.reshaka.taskengine.domain.port;

import ru.reshaka.taskengine.domain.model.TaskManual;

public interface ManualTaskValidatorPort {
    boolean isCorrectAnswer(Object answer, TaskManual task);
}
