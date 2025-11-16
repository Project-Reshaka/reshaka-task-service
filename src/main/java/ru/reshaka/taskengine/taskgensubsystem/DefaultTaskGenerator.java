package ru.reshaka.taskengine.taskgensubsystem;

import java.util.*;

public class DefaultTaskGenerator implements TaskGenerator {

    private final List<Stage> stages = List.of(
            new TextStage(),
            new VariableDeclarationStage(),
            new FilterStage(),
            new AnswerStage()
    );

    @Override
    public List<GeneratedTask> generate(String dsl, int maxCount, int maxAttempts) throws InterruptedException {
        TaskContext ctx = new TaskContext();
        ctx.setRawText(dsl);

        for (Stage s : stages) {
            s.apply(ctx);
        }

        if (ctx.getVariables() == null) {
            ctx.setVariables(Collections.emptyMap());
        }

        // Treat empty variables as finite (one combination)
        boolean allFinite = ctx.getVariables().isEmpty()
                || ctx.getVariables().values().stream().allMatch(v -> v.getGenerator().isFinite());

        SequenceGenerator gen = allFinite ? new CartesianSequenceGenerator() : new RandomSequenceGenerator();
        gen.init(ctx);

        List<GeneratedTask> out = new ArrayList<>(maxCount);
        int produced = 0;

        int attempts = 0;
        while (produced < maxCount) {
            if (++attempts > maxAttempts) break;
            Map<String, Object> vals = gen.next();
            if (vals == null) break;

            boolean ok = true;
            if (ctx.getFilters() != null) {
                for (Filter f : ctx.getFilters()) {
                    if (!f.test(vals)) {
                        ok = false;
                        break;
                    }
                }
            }
            if (!ok) continue;

            List<String> answers = new ArrayList<>();
            if (ctx.getAnswers() != null) {
                for (AnswerExpression ae : ctx.getAnswers()) {
                    answers.addAll(ae.evaluateAll(vals));
                }
            }

            String formatted = ctx.getFormattableText().format(vals);

            out.add(new GeneratedTask(formatted, vals, answers));
            produced++;
        }

        gen.close();
        return out;
    }
}
