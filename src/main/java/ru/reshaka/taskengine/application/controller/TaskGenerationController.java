package ru.reshaka.taskengine.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.reshaka.taskengine.application.dto.*;
import ru.reshaka.taskengine.application.service.TaskGenerationAppService;

@RestController
@RequestMapping("/tasks/generate")
@RequiredArgsConstructor
public class TaskGenerationController {

    private final TaskGenerationAppService generationService;

    @PostMapping
    public GenerateTasksResponse generate(@RequestBody GenerateTasksRequest request) throws InterruptedException {
        return generationService.generateTasks(request);
    }
}
