package ru.reshaka.taskengine.taskgensubsystem;


import ru.reshaka.taskengine.taskgensubsystem.TaskContext;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomChoiceGenerator implements VariableGenerator {

    private final List<Object> values;

    public RandomChoiceGenerator(List<?> values) {
        this.values = new ArrayList<>(values);
    }

    @Override
    public boolean isFinite() { return true; }

    @Override
    public List<Object> allValues(TaskContext ctx) {
        return values;
    }

    @Override
    public Object sample(TaskContext ctx) {
        int idx = ThreadLocalRandom.current().nextInt(values.size());
        return values.get(idx);
    }
}
