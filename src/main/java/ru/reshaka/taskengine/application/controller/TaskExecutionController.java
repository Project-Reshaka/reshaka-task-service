package ru.reshaka.taskengine.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.reshaka.taskengine.application.service.TaskExecutionAppService;
import ru.reshaka.taskengine.application.dto.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskExecutionController {

    private final TaskExecutionAppService appService;

    @PostMapping("/{taskId}/submit")
    public TaskAnswerResponse submitAnswer(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long taskId,
            @RequestBody Object answer
    ) {
        TaskAnswerRequest request = TaskAnswerRequest.builder()
                .taskId(taskId)
                .answer(answer)
                .build();

        return appService.submitAnswer(userId, request);
    }

    @PostMapping("/tasks/manual/review")
    public ManualReviewResponse review(@RequestHeader("X-User-Id") Long userId, @RequestBody ManualReviewRequest req) {
        return appService.reviewManualTask(userId, req);
    }
}
