package ru.reshaka.taskengine.infra.postgre.mapper;

import ru.reshaka.taskengine.domain.model.ResultTask;
import ru.reshaka.taskengine.domain.model.ResultVariant;
import ru.reshaka.taskengine.domain.model.ResultVariantTask;
import ru.reshaka.taskengine.domain.model.TaskBaseType;
import ru.reshaka.taskengine.infra.postgre.model.ResultTaskEntity;
import ru.reshaka.taskengine.infra.postgre.model.ResultVariantEntity;
import ru.reshaka.taskengine.infra.postgre.model.ResultVariantTaskEntity;
import ru.reshaka.taskengine.infra.postgre.util.JsonUtils;

import java.time.Instant;

public class ResultMapper {

    public ResultTaskEntity toEntity(ResultTask domain) {
        return ResultTaskEntity.builder()
                .id(domain.getId() != null ? Long.parseLong(domain.getId().toString().replaceAll("[^0-9]", "").substring(0, 9)) : null)
                .userId(domain.getUserId())
                .taskId(domain.getTaskId())
                .taskType(domain.getTaskType().name().toLowerCase())
                .selectedAnswer(JsonUtils.toJson(domain.getSelectedAnswer()))
                .isCorrect(domain.getIsCorrect())
                .answeredAt(Instant.now())
                .build();
    }

    public ResultVariantEntity toEntity(ResultVariant domain) {
        return ResultVariantEntity.builder()
                .userId(domain.getUserId())
                .variantId(domain.getVariantId())
                .startedAt(domain.getStartTime())
                .finishedAt(domain.getEndTime())
                .totalQuestions(domain.getTotalQuestions())
                .correctAnswers(domain.getCorrectAnswers())
                .timeTakenSec((int) domain.getTotalTimeSec())
                .build();
    }

    public ResultVariantTaskEntity toEntity(ResultVariantTask domain, ResultVariantEntity parent) {
        return ResultVariantTaskEntity.builder()
                .resultVariant(parent)
                .taskType(domain.getTaskType().name().toLowerCase())
                .taskId(domain.getTaskId())
                .selectedAnswer(JsonUtils.toJson(domain.getSelectedAnswer()))
                .isCorrect(domain.getIsCorrect())
                .build();
    }

    public ResultTask toDomain(ResultTaskEntity e) {
        return ResultTask.builder()
                .userId(e.getUserId())
                .taskId(e.getTaskId())
                .taskType(TaskBaseType.valueOf(e.getTaskType().toUpperCase()))
                .selectedAnswer(JsonUtils.fromJson(e.getSelectedAnswer(), Object.class))
                .isCorrect(e.getIsCorrect())
                .build();
    }

    public ResultVariant toDomain(ResultVariantEntity e) {
        return ResultVariant.builder()
                .userId(e.getUserId())
                .variantId(e.getVariantId())
                .startTime(e.getStartedAt())
                .endTime(e.getFinishedAt())
                .totalQuestions(e.getTotalQuestions())
                .correctAnswers(e.getCorrectAnswers())
                .totalTimeSec(e.getTimeTakenSec() != null ? e.getTimeTakenSec() : 0)
                .build();
    }

}

