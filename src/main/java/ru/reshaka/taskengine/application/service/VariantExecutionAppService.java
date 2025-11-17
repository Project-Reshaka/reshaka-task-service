package ru.reshaka.taskengine.application.service;

import lombok.RequiredArgsConstructor;
import ru.reshaka.taskengine.application.dto.*;
import ru.reshaka.taskengine.domain.model.ResultVariant;
import ru.reshaka.taskengine.domain.service.VariantExecutionService;

@RequiredArgsConstructor
public class VariantExecutionAppService {

    private final VariantExecutionService variantExecution;

    public VariantStartResponse start(Long userId, VariantStartRequest request) {
        variantExecution.start(request.getVariantId(), userId);
        return VariantStartResponse.builder()
                .variantId(request.getVariantId())
                .userId(userId)
                .startTimestamp(System.currentTimeMillis())
                .build();
    }

    public VariantFinishResponse finish(Long userId, VariantFinishRequest request) {
        ResultVariant resultVariant = variantExecution.finish(request.getVariantId(), userId, request.getAnswers());

        return VariantFinishResponse.builder()
                .variantId(request.getVariantId())
                .userId(userId)
                .totalQuestions(resultVariant.getTotalQuestions())
                .correctAnswers(resultVariant.getCorrectAnswers())
                .totalTimeSec(resultVariant.getTotalTimeSec())
                .build();
    }
}
