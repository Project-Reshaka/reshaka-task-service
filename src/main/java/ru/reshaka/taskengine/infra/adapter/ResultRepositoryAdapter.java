package ru.reshaka.taskengine.infra.adapter;

import lombok.RequiredArgsConstructor;
import ru.reshaka.taskengine.domain.model.ResultTask;
import ru.reshaka.taskengine.domain.model.ResultVariant;
import ru.reshaka.taskengine.domain.model.ResultVariantTask;
import ru.reshaka.taskengine.domain.port.ResultRepositoryPort;
import ru.reshaka.taskengine.infra.postgre.mapper.ResultMapper;
import ru.reshaka.taskengine.infra.postgre.model.ResultTaskEntity;
import ru.reshaka.taskengine.infra.postgre.model.ResultVariantEntity;
import ru.reshaka.taskengine.infra.postgre.model.ResultVariantTaskEntity;
import ru.reshaka.taskengine.infra.postgre.repo.ResultTaskJpaRepository;
import ru.reshaka.taskengine.infra.postgre.repo.ResultVariantJpaRepository;
import ru.reshaka.taskengine.infra.postgre.repo.ResultVariantTaskJpaRepository;
import ru.reshaka.taskengine.infra.postgre.util.JsonUtils;

import java.util.List;

@RequiredArgsConstructor
public class ResultRepositoryAdapter implements ResultRepositoryPort {

    private final ResultTaskJpaRepository taskRepo;
    private final ResultVariantJpaRepository variantRepo;
    private final ResultVariantTaskJpaRepository variantTaskRepo;
    private final ResultMapper mapper;

    @Override
    public void saveResultTask(ResultTask resultTask) {
        ResultTaskEntity entity = mapper.toEntity(resultTask);
        taskRepo.save(entity);
    }

    @Override
    public ResultVariant saveResultVariant(ResultVariant resultVariant, List<ResultVariantTask> taskResults) {
        ResultVariantEntity variantEntity = mapper.toEntity(resultVariant);
        ResultVariantEntity saved = variantRepo.save(variantEntity);

        List<ResultVariantTaskEntity> taskEntities = taskResults.stream()
                .map(t -> mapper.toEntity(t, saved))
                .toList();

        variantTaskRepo.saveAll(taskEntities);
        return resultVariant;
    }

    @Override
    public void saveResultVariantTask(ResultVariantTask resultVariantTask) {
        // Вариант без родителя (например, при поэтапной записи)
        ResultVariantTaskEntity entity = ResultVariantTaskEntity.builder()
                .taskType(resultVariantTask.getTaskType().name().toLowerCase())
                .taskId(resultVariantTask.getTaskId())
                .selectedAnswer(JsonUtils.toJson(resultVariantTask.getSelectedAnswer()))
                .isCorrect(resultVariantTask.getIsCorrect())
                .build();
        variantTaskRepo.save(entity);
    }

    @Override
    public List<ResultTask> findTaskResultsByUser(Long userId) {
        return taskRepo.findByUserId(userId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<ResultVariant> findVariantResultsByUser(Long userId) {
        return variantRepo.findByUserId(userId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}

