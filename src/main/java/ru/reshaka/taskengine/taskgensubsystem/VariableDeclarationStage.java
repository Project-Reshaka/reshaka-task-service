package ru.reshaka.taskengine.taskgensubsystem;

import ru.reshaka.taskengine.taskgensubsystem.*;

import java.util.*;

public class VariableDeclarationStage implements Stage {

    @Override
    public void apply(TaskContext context) {
        Map<String, Map<String,Object>> decl = context.getVariableDeclarations();
        if (decl == null || decl.isEmpty()) {
            context.setVariables(Collections.emptyMap());
            return;
        }

        Map<String, Variable> vars = new LinkedHashMap<>();
        for (Map.Entry<String, Map<String,Object>> e : decl.entrySet()) {
            String name = e.getKey();
            Map<String,Object> cfg = e.getValue();
            VariableGenerator g = resolveGenerator(cfg);
            vars.put(name, new Variable(name, g));
        }
        context.setVariables(vars);

    }

    private VariableGenerator resolveGenerator(Map<String,Object> cfg) {
        Object typeObj = cfg.get("type");
        if (!(typeObj instanceof String))
            throw new StageException("variable.type must be string");

        String type = (String) typeObj;

        switch (type) {
            case "int":
                return resolveInt(cfg);

            case "float":
                return resolveFloat(cfg);

            case "choice":
                return resolveChoice(cfg);

            default:
                throw new StageException("unknown variable type: " + type);
        }
    }

    private VariableGenerator resolveInt(Map<String,Object> cfg) {
        Object fromObj = cfg.get("from");
        Object toObj = cfg.get("to");

        if (!(fromObj instanceof Number) || !(toObj instanceof Number))
            throw new StageException("int variable must have numeric 'from' and 'to'");

        int from = ((Number) fromObj).intValue();
        int to = ((Number) toObj).intValue();

        return new RandomIntGenerator(from, to);
    }

    private VariableGenerator resolveFloat(Map<String,Object> cfg) {
        Object fromObj = cfg.get("from");
        Object toObj = cfg.get("to");
        Object digObj = cfg.get("digits");

        if (!(fromObj instanceof Number) || !(toObj instanceof Number))
            throw new StageException("float variable must have numeric 'from' and 'to'");

        int digits = digObj instanceof Number ? ((Number) digObj).intValue() : 2;

        double from = ((Number) fromObj).doubleValue();
        double to = ((Number) toObj).doubleValue();

        return new RandomFloatGenerator(from, to, digits);
    }

    private VariableGenerator resolveChoice(Map<String,Object> cfg) {
        Object valsObj = cfg.get("values");
        if (!(valsObj instanceof List))
            throw new StageException("choice variable must have list 'values'");

        List<?> values = (List<?>) valsObj;

        return new RandomChoiceGenerator(values);
    }
}
