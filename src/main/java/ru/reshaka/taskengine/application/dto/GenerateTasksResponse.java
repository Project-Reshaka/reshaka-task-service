package ru.reshaka.taskengine.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class GenerateTasksResponse {
    private List<TaskDto> tasks;

    @Data
    @AllArgsConstructor
    public static class TaskDto {
        private String text;
        private Map<String, Object> variableValues;
        private List<String> answers;
    }
}
