package ru.reshaka.taskengine.taskgensubsystem;

import java.util.List;
import java.util.Map;

public interface AnswerExpression {
    List<String> evaluateAll(Map<String, Object> variables);
}
