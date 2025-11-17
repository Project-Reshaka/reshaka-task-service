package ru.reshaka.taskengine.application.service;

import lombok.RequiredArgsConstructor;
import ru.reshaka.taskengine.domain.model.ManualTaskValidator;
import ru.reshaka.taskengine.infra.adapter.ManualTaskValidatorFactory;
import ru.reshaka.taskengine.infra.postgre.model.TaskManualEntity;

@RequiredArgsConstructor
public class StorageManualTaskValidatorFactory implements ManualTaskValidatorFactory {

    private final ManualAnswerReviewPort reviewPort;

    @Override
    public <T> ManualTaskValidator<T> create(TaskManualEntity task) {
        return new StorageManualTaskValidator<>(reviewPort);
    }
}
