package ru.reshaka.taskengine.taskgensubsystem;

import java.util.List;

public interface TaskGenerator {
    List<GeneratedTask> generate(String dsl, int maxCount, int maxAttempts) throws InterruptedException;
}
