package ru.reshaka.taskengine.taskgensubsystem;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import java.text.DecimalFormat;
import java.util.*;

public class MXParserAnswerExpression implements AnswerExpression {

    private final List<String> expressions;
    private final DecimalFormat df = new DecimalFormat("0.############"); // or configurable

    public MXParserAnswerExpression(String raw) {
        if (raw == null) {
            this.expressions = Collections.emptyList();
        } else {
            String[] parts = raw.split(";");
            List<String> list = new ArrayList<>();
            for (String p : parts) {
                String t = p.trim();
                if (!t.isEmpty()) list.add(t);
            }
            this.expressions = list;
        }
    }

    @Override
    public List<String> evaluateAll(Map<String, Object> variables) {
        List<String> result = new ArrayList<>(expressions.size());
        for (String expr : expressions) {
            Expression e = new Expression(expr);
            for (Map.Entry<String, Object> en : variables.entrySet()) {
                Object val = en.getValue();
                if (val instanceof Number) {
                    Argument a = new Argument(en.getKey(), ((Number) val).doubleValue());
                    e.addArguments(a);
                }
            }
            double calc = e.calculate();
            if (Double.isNaN(calc)) {
                result.add("NaN");
            } else {
                result.add(df.format(calc));
            }
        }
        return result;
    }
}
