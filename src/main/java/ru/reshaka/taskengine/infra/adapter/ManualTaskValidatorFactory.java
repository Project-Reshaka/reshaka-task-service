package ru.reshaka.taskengine.infra.adapter;

import ru.reshaka.taskengine.domain.model.ManualTaskValidator;
import ru.reshaka.taskengine.domain.model.TaskManual;
import ru.reshaka.taskengine.infra.postgre.model.TaskManualEntity;

public interface ManualTaskValidatorFactory {

    <T> ManualTaskValidator<T> create(TaskManualEntity task);

}
