package ru.reshaka.taskengine.application.service;

import lombok.RequiredArgsConstructor;
import ru.reshaka.taskengine.domain.model.*;
import ru.reshaka.taskengine.domain.port.*;
import ru.reshaka.taskengine.application.dto.*;
import ru.reshaka.taskengine.domain.service.SingleTaskValidationService;

@RequiredArgsConstructor
public class TaskExecutionAppService {

    private final SingleTaskValidationService taskValidator;
    private final TaskRepositoryPort taskRepo;
    private final TaskAnswerParser parser;
    private final ManualAnswerReviewPort manualReviewPort;


    public TaskAnswerResponse submitAnswer(Long userId, TaskAnswerRequest request) {
        TaskBase task = taskRepo.findById(request.getTaskId(), request.getBaseType())
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        Object parsedAnswer = parser.parse(request.getBaseType(), request.getAnswer());

        ResultTask result = taskValidator.validateAndPersist(task, parsedAnswer, userId);

        return TaskAnswerResponse.builder()
                .taskId(result.getTaskId())
                .selectedAnswer(result.getSelectedAnswer())
                .isCorrect(Boolean.TRUE.equals(result.getIsCorrect()))
                .build();
    }


    public ManualReviewResponse reviewManualTask(Long userId, ManualReviewRequest request) {
        manualReviewPort.saveReview(userId, request.getTaskId(), request.getIsCorrect());

        return ManualReviewResponse.builder()
                .taskId(request.getTaskId())
                .userId(userId)
                .isCorrect(request.getIsCorrect())
                .status("saved")
                .build();
    }
}
