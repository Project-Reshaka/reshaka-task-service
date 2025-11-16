package ru.reshaka.taskengine.taskgensubsystem;


import ru.reshaka.taskengine.taskgensubsystem.TaskContext;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntGenerator implements VariableGenerator {

    private final int from;
    private final int to;

    public RandomIntGenerator(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isFinite() { return true; }

    @Override
    public List<Object> allValues(TaskContext ctx) {
        List<Object> list = new ArrayList<>(to - from + 1);
        for (int i = from; i <= to; i++) list.add(i);
        return list;
    }

    @Override
    public Object sample(TaskContext ctx) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }
}
