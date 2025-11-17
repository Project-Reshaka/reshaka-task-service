package ru.reshaka.taskengine.domain.service;

import lombok.RequiredArgsConstructor;
import ru.reshaka.taskengine.domain.model.*;
import ru.reshaka.taskengine.domain.port.ResultRepositoryPort;
import ru.reshaka.taskengine.domain.port.TaskRepositoryPort;


@RequiredArgsConstructor
public class SingleTaskValidationService {

    private final ResultRepositoryPort resultRepo;
    private final TaskRepositoryPort taskRepo;

    public ResultTask validateAndPersist(TaskBase task, Object answer, Long userId) {

        boolean isCorrect = task.validateAnswer(answer,userId).getIsCorrect();


        ResultTask result = ResultTask.builder()
                .taskId(task.getId())
                .userId(userId)
        //        .id(UUID.randomUUID())
                .selectedAnswer(answer)
                .isCorrect(isCorrect)
                .build();

        resultRepo.saveResultTask(result);
        return result;
    }

    public ResultVariantTask validateAndPersistVariant(VariantTask task, Object answer, Long userId) {
        boolean isCorrect = taskRepo
                .findById(task.getTaskId(), task.getTaskType())
                .orElseThrow()
                .validateAnswer(answer, userId).getIsCorrect();

        ResultVariantTask resultVariantTask = ResultVariantTask.builder()
                .taskId(task.getTaskId())
                .resultVariantId(task.getVariantId())
                .userId(userId)
        //        .id(UUID.randomUUID())
                .selectedAnswer(answer)
                .isCorrect(isCorrect)
                .taskType(task.getTaskType())
                .build();

        resultRepo.saveResultVariantTask(resultVariantTask);
        return resultVariantTask;

    }
}
