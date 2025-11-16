package ru.reshaka.taskengine.taskgensubsystem;

import java.util.Map;

public interface SequenceGenerator {
    void init(TaskContext taskContext);
    Map<String, Object> next();
    void close();
}
