package ru.reshaka.taskengine.taskgensubsystem;

import java.util.List;

public interface VariableGenerator {
    boolean isFinite();
    List<Object> allValues(TaskContext context);
    Object sample(TaskContext context);
}
