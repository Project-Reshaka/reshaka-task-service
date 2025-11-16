package ru.reshaka.taskengine.taskgensubsystem;

import java.util.HashMap;
import java.util.Map;

public class RandomSequenceGenerator implements SequenceGenerator {

    private TaskContext taskContext;

    @Override
    public void init(TaskContext taskContext) {
        this.taskContext = taskContext;
    }

    @Override
    public Map<String, Object> next() {
        var vars = taskContext.getVariables();
        Map<String, Object> m = new HashMap<>();
        for (Map.Entry<String, Variable> e : vars.entrySet()) {
            m.put(e.getKey(), e.getValue().getGenerator().sample(taskContext)); // передаём контекст
        }
        return m; // random generator is effectively infinite; caller must use attempts limit
    }

    @Override
    public void close() { }
}
