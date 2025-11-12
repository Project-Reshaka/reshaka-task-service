package ru.reshaka.taskengine.domain.service;

import lombok.RequiredArgsConstructor;
import ru.reshaka.taskengine.domain.model.*;
import ru.reshaka.taskengine.domain.port.*;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class VariantExecutionService {

    private final VariantRepositoryPort variantRepo;
    private final ResultRepositoryPort resultRepo;
    private final TimerPort timer;
    private final SingleTaskValidationService taskValidator;

    private final Map<Long, Long> times = new ConcurrentHashMap<>();


    public void start(Long variantId, Long userId) {
        timer.startTimer(variantId, userId);
        times.put(variantId, Instant.now().toEpochMilli());
    }

    public void finish(Long variantId, Long userId, Map<Long, Object> answers) {
        timer.stopTimer(variantId, userId);
        Instant end = Instant.now();
        Instant start = Instant.ofEpochMilli(times.get(variantId));

        Variant variant = variantRepo.findById(variantId)
                .orElseThrow(() -> new IllegalArgumentException("Variant not found"));

        List<ResultVariantTask> taskResults = validateVariant(variant, userId, answers);

        ResultVariant resultVariant = ResultVariant.builder()
                .variantId(variantId)
                .userId(userId)
                .startTime(start)
                .endTime(end)
                .totalQuestions(taskResults.size())
                .correctAnswers((int) taskResults.stream().filter(ResultVariantTask::getIsCorrect).count())
                .totalTimeSec(Duration.between(start, end).toSeconds())
                .build();

        resultRepo.saveResultVariant(resultVariant, taskResults);
    }

    private List<ResultVariantTask> validateVariant(Variant variant, Long userId, Map<Long, Object> answers) {
        return variant.getTasks().stream()
                .map(task -> taskValidator.validateAndPersistVariant(task, answers.get(task.getTaskId()), userId))
                .toList();
    }

}
