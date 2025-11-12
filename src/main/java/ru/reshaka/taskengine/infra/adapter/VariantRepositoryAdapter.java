package ru.reshaka.taskengine.infra.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.reshaka.taskengine.domain.model.Variant;
import ru.reshaka.taskengine.domain.model.VariantTask;
import ru.reshaka.taskengine.domain.port.VariantRepositoryPort;
import ru.reshaka.taskengine.infra.postgre.mapper.VariantTaskMapper;
import ru.reshaka.taskengine.infra.postgre.model.VariantEntity;
import ru.reshaka.taskengine.infra.postgre.model.VariantTaskEntity;
import ru.reshaka.taskengine.infra.postgre.repo.VariantJpaRepository;
import ru.reshaka.taskengine.infra.postgre.repo.VariantTaskJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class VariantRepositoryAdapter implements VariantRepositoryPort {

    private final VariantJpaRepository variantRepo;
    private final VariantTaskJpaRepository variantTaskRepo;
    private final VariantTaskMapper variantTaskMapper;

    @Override
    public Optional<Variant> findById(Long variantId) {
        return variantRepo.findById(variantId)
                .map(entity -> {
                    List<VariantTaskEntity> taskEntities = variantTaskRepo.findByVariantId(entity.getId());
                    return variantTaskMapper.toDomain(entity, taskEntities);
                });
    }

    @Override
    public List<Variant> findBySubject(Long subjectId) {
        return variantRepo.findBySubjectId(subjectId).stream()
                .map(entity -> {
                    List<VariantTaskEntity> taskEntities = variantTaskRepo.findByVariantId(entity.getId());
                    return variantTaskMapper.toDomain(entity, taskEntities);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Variant save(Variant variant) {
        VariantEntity entity = variantTaskMapper.toEntity(variant);
        VariantEntity saved = variantRepo.save(entity);

        if (variant.getId() != null) {
            variantTaskRepo.findByVariantId(variant.getId())
                    .forEach(task -> variantTaskRepo.deleteById(task.getId()));
        }

        if (variant.getTasks() != null) {
            for (VariantTask t : variant.getTasks()) {
                VariantTaskEntity te = VariantTaskEntity.builder()
                        .variant(saved)
                        .taskId(t.getTaskId())
                        .taskType(String.valueOf(t.getTaskType()))
                        .orderNum(t.getOrder())
                        .build();
                variantTaskRepo.save(te);
            }
        }

        return variantTaskMapper.mapToVariant(saved, variant.getTasks());
    }

    @Override
    public void delete(Long variantId) {
        variantTaskRepo.findByVariantId(variantId)
                .forEach(task -> variantTaskRepo.deleteById(task.getId()));
        variantRepo.deleteById(variantId);
    }

}
