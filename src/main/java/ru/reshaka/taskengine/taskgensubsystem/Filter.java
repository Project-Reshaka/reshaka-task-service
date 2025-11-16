package ru.reshaka.taskengine.taskgensubsystem;

import java.util.Map;

@FunctionalInterface
public interface Filter {
    boolean test(Map<String, Object> variables);
}
