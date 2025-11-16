// src/main/java/ru/reshaka/taskengine/taskgensubsystem/TaskContext.java
package ru.reshaka.taskengine.taskgensubsystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskContext {
    private String rawText;

    private FormattableText formattableText;
    private Map<String, Variable> variables;
    private List<Filter> filters;
    private List<AnswerExpression> answers;

    private Map<String, Map<String,Object>> variableDeclarations;
    private List<String> filterDeclarations;
    private List<String> answerDeclarations;
}



