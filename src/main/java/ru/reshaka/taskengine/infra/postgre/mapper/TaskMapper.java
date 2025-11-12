package ru.reshaka.taskengine.infra.postgre.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import ru.reshaka.taskengine.domain.model.ManualTaskValidator;
import ru.reshaka.taskengine.domain.model.TaskChoice;
import ru.reshaka.taskengine.domain.model.TaskInput;
import ru.reshaka.taskengine.domain.model.TaskManual;
import ru.reshaka.taskengine.infra.adapter.ManualTaskValidatorFactory;
import ru.reshaka.taskengine.infra.postgre.model.TaskChoiceEntity;
import ru.reshaka.taskengine.infra.postgre.model.TaskInputEntity;
import ru.reshaka.taskengine.infra.postgre.model.TaskManualEntity;
import ru.reshaka.taskengine.infra.postgre.util.JsonUtils;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
public class TaskMapper {

    private final ManualTaskValidatorFactory manualTaskValidatorFactory;

    public TaskChoice toDomain(TaskChoiceEntity e) {
        TaskChoice taskChoice = new TaskChoice();
        taskChoice.setId(e.getId());
        taskChoice.setText(e.getText());
        taskChoice.setHard(e.getIsHard());
        taskChoice.setCreatedAt(e.getCreatedAt());
        taskChoice.setUpdatedAt(e.getUpdatedAt());
        taskChoice.setOptions(JsonUtils.fromJson(e.getOptions(), new TypeReference<List<TaskChoice.Option>>() {}));
        taskChoice.setCorrectAnswer(JsonUtils.fromJson(e.getCorrectAnswer(), new TypeReference<List<Integer>>() {}));
        return taskChoice;
    }

    public TaskInput toDomain(TaskInputEntity e) {
        TaskInput taskInput = new TaskInput();
        taskInput.setId(e.getId());
        taskInput.setText(e.getText());
        taskInput.setHard(e.getIsHard());
        taskInput.setCreatedAt(e.getCreatedAt());
        taskInput.setUpdatedAt(e.getUpdatedAt());
        taskInput.setCorrectAnswers(JsonUtils.fromJson(e.getCorrectAnswers(), new TypeReference<List<String>>() {}));
        return taskInput;
    }

    public TaskManual toDomain(TaskManualEntity e) {
        ManualTaskValidator taskValidator = manualTaskValidatorFactory.createManualTaskValidator(e);
        TaskManual taskManual = new TaskManual(taskValidator);
        taskManual.setId(e.getId());
        taskManual.setText(e.getText());
        taskManual.setHard(e.getIsHard());
        taskManual.setCreatedAt(e.getCreatedAt());
        taskManual.setUpdatedAt(e.getUpdatedAt());

        return taskManual;
    }
}

