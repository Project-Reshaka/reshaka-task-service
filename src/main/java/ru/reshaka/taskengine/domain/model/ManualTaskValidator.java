package ru.reshaka.taskengine.domain.model;

public interface ManualTaskValidator {

    Boolean isCorrectAnswer(Object answer, TaskManual task);

}
