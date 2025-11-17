package ru.reshaka.taskengine.application.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.reshaka.taskengine.domain.model.ManualTaskValidator;
import ru.reshaka.taskengine.domain.model.TaskManual;

@RequiredArgsConstructor
public class StorageManualTaskValidator<T> implements ManualTaskValidator<T> {

    private final ManualAnswerReviewPort reviewPort;

    @Override
    public Boolean isCorrectAnswer(Long userId, T answer, TaskManual<T> task) {
        return reviewPort.findReview(userId, task.getId());
    }
}


