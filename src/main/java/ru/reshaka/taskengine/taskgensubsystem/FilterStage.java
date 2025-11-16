package ru.reshaka.taskengine.taskgensubsystem;


import java.util.*;

public class FilterStage implements Stage {

    @Override
    public void apply(TaskContext context) {
        List<String> raw = context.getFilterDeclarations();
        if (raw == null || raw.isEmpty()) {
            context.setFilters(Collections.emptyList());
            return;
        }

        List<Filter> result = new ArrayList<>(raw.size());
        for (String f : raw) {
            result.add(new MXParserFilter(f));
        }

        context.setFilters(result);
    }
}

