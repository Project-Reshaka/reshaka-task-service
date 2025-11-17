package ru.reshaka.taskengine.application.service;

import lombok.RequiredArgsConstructor;
import ru.reshaka.taskengine.application.dto.*;
import ru.reshaka.taskengine.taskgensubsystem.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TaskGenerationAppService {

    private final TaskGenerator generator;

    public GenerateTasksResponse generateTasks(GenerateTasksRequest request) throws InterruptedException {
        List<GeneratedTask> tasks = generator.generate(request.getDsl(), request.getMaxCount(), request.getMaxAttempts());

        List<GenerateTasksResponse.TaskDto> dtos = tasks.stream()
                .map(t -> new GenerateTasksResponse.TaskDto(t.getText(), t.getVariableValues(), t.getAnswers()))
                .toList();

        return new GenerateTasksResponse(dtos);
    }
}
