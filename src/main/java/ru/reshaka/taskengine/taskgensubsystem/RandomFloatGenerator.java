package ru.reshaka.taskengine.taskgensubsystem;

import ru.reshaka.taskengine.taskgensubsystem.TaskContext;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomFloatGenerator implements VariableGenerator {

    private final double from;
    private final double to;
    private final int digits;

    public RandomFloatGenerator(double from, double to, int digits) {
        this.from = from;
        this.to = to;
        this.digits = digits;
    }

    @Override
    public boolean isFinite() { return false; }

    @Override
    public List<Object> allValues(TaskContext ctx) {
        return Collections.emptyList();
    }

    @Override
    public Object sample(TaskContext ctx) {
        double v = ThreadLocalRandom.current().nextDouble(from, to);
        double scale = Math.pow(10, digits);
        return Math.round(v * scale) / scale;
    }
}
