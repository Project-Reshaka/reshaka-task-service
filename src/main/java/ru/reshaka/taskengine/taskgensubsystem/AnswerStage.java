package ru.reshaka.taskengine.taskgensubsystem;

import ru.reshaka.taskengine.taskgensubsystem.*;

import java.util.*;

public class AnswerStage implements Stage {

    @Override
    public void apply(TaskContext context) {
        List<String> raw = context.getAnswerDeclarations();
        if (raw == null || raw.isEmpty()) {
            context.setAnswers(Collections.emptyList());
            return;
        }

        List<AnswerExpression> result = new ArrayList<>(raw.size());
        for (String line : raw) {
            result.add(new MXParserAnswerExpression(line));
        }

        context.setAnswers(result);
    }
}
