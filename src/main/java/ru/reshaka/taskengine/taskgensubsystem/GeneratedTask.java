package ru.reshaka.taskengine.taskgensubsystem;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class GeneratedTask {
    private String text;
    private Map<String, Object> variableValues;
    private List<String> answers;
}
