package ru.reshaka.taskengine.taskgensubsystem;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.Map;

public class MXParserFilter implements Filter {

    private final String expression;

    public MXParserFilter(String expression) {
        this.expression = expression;
    }

    @Override
    public boolean test(Map<String, Object> variables) {
        Expression exp = new Expression(expression);
        for (Map.Entry<String, Object> en : variables.entrySet()) {
            Object val = en.getValue();
            if (val instanceof Number) {
                Argument a = new Argument(en.getKey(), ((Number) val).doubleValue());
                exp.addArguments(a);
            }
        }
        double val = exp.calculate();
        if (Double.isNaN(val)) return false;
        return val != 0.0;
    }
}
