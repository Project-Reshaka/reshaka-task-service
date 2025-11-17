package ru.reshaka.taskengine.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class GenerateTasksRequest {
    private String dsl;       // YAML/DSL описание задачи
    private int maxCount;     // сколько задач хотим сгенерировать
    private int maxAttempts;  // сколько попыток генерации
}

