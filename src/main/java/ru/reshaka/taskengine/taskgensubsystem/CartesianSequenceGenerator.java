package ru.reshaka.taskengine.taskgensubsystem;

import java.util.*;

public class CartesianSequenceGenerator implements SequenceGenerator {

    private List<String> names;
    private List<List<Object>> domains;
    private int[] indices;
    private boolean done;

    @Override
    public void init(TaskContext taskContext) {
        var variables = taskContext.getVariables();
        names = new ArrayList<>(variables.keySet());
        domains = new ArrayList<>(names.size());

        for (String n : names) {
            Variable v = variables.get(n);
            if (!v.getGenerator().isFinite()) {
                throw new IllegalStateException("Infinite variable in CartesianSequenceGenerator: " + n);
            }
            List<Object> values = v.getGenerator().allValues(taskContext);
            if (values == null || values.isEmpty()) {
                throw new IllegalStateException("Empty domain for variable: " + n);
            }
            domains.add(values);
        }

        indices = new int[names.size()];
        Arrays.fill(indices, 0);
        done = false;
    }

    @Override
    public Map<String, Object> next() {
        if (names.isEmpty()) {
            if (done) return null;
            done = true;
            return Collections.emptyMap();
        }

        if (done) return null;

        Map<String, Object> result = new HashMap<>(names.size());
        for (int i = 0; i < names.size(); i++) {
            result.put(names.get(i), domains.get(i).get(indices[i]));
        }

        for (int i = indices.length - 1; i >= 0; i--) {
            indices[i]++;
            if (indices[i] < domains.get(i).size()) break;
            indices[i] = 0;
            if (i == 0) done = true;
        }

        return result;
    }

    @Override
    public void close() {
    }
}
