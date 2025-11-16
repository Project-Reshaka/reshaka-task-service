package ru.reshaka.taskengine.taskgensubsystem;


import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class TextStage implements Stage {

    private final Yaml yaml = new Yaml();

    @Override
    public void apply(TaskContext context) {
        if (context.getRawText() == null || context.getRawText().isBlank()) {
            throw new StageException("rawText is empty");
        }

        Object parsedObj;
        try {
            parsedObj = yaml.load(context.getRawText());
        } catch (Exception e) {
            throw new StageException("Failed to parse DSL YAML", e);
        }

        if (!(parsedObj instanceof Map)) {
            throw new StageException("DSL root must be a map");
        }

        Map<String, Object> root = (Map<String, Object>) parsedObj;

        Object textObj = root.get("text");
        if (!(textObj instanceof String)) {
            throw new StageException("Missing or invalid 'text' field");
        }

        context.setFormattableText(new FormattableText((String) textObj));

        Object varsObj = root.get("variables");
        Map<String, Map<String,Object>> varsDecl = null;
        if (varsObj instanceof Map) {
            varsDecl = (Map<String, Map<String, Object>>) varsObj;
        }

        Object filtersObj = root.get("filters");
        List<String> filterDecl = null;
        if (filtersObj instanceof List) {
            filterDecl = (List<String>) filtersObj;
        }

        Object answersObj = root.get("answers");
        List<String> answerDecl = null;
        if (answersObj instanceof List) {
            answerDecl = (List<String>) answersObj;
        }

        context.setVariableDeclarations(varsDecl);
        context.setFilterDeclarations(filterDecl);
        context.setAnswerDeclarations(answerDecl);
    }
}