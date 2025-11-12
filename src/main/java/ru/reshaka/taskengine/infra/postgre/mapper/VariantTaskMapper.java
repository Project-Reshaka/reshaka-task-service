package ru.reshaka.taskengine.infra.postgre.mapper;

import ru.reshaka.taskengine.domain.model.TaskBaseType;
import ru.reshaka.taskengine.domain.model.Variant;
import ru.reshaka.taskengine.domain.model.VariantTask;
import ru.reshaka.taskengine.infra.postgre.model.VariantEntity;
import ru.reshaka.taskengine.infra.postgre.model.VariantTaskEntity;

import java.util.List;
import java.util.stream.Collectors;

public class VariantTaskMapper {



    public Variant toDomain(VariantEntity entity, List<VariantTaskEntity> taskEntities) {
        List<VariantTask> tasks = taskEntities.stream()
                .map(t -> VariantTask.builder()
                        .variantId(entity.getId())
                        .taskId(t.getTaskId())
                        .taskType(TaskBaseType.valueOf(t.getTaskType()))
                        .order(t.getOrderNum())
                        .build())
                .collect(Collectors.toList());

        return mapToVariant(entity, tasks);
    }

    public Variant mapToVariant(VariantEntity entity, List<VariantTask> tasks) {
        return Variant.builder()
                .id(entity.getId())
                .subjectId(entity.getSubject() != null ? entity.getSubject().getId() : null)
                .authorId(entity.getAuthor() != null ? entity.getAuthor().getId() : null)
                .name(entity.getName())
                .description(entity.getDescription())
                .hasTimer(Boolean.TRUE.equals(entity.getIsTimed()))
                .timerLimitSeconds(entity.getTimeLimitSec() != null ? entity.getTimeLimitSec().longValue() : null)
                .tasks(tasks)
                .build();
    }

    public VariantEntity toEntity(Variant variant) {
        return VariantEntity.builder()
                .id(variant.getId())
                .name(variant.getName())
                .description(variant.getDescription())
                .isTimed(variant.isHasTimer())
                .timeLimitSec(variant.getTimerLimitSeconds() != null ? variant.getTimerLimitSeconds().intValue() : null)
                .build();
    }

}
