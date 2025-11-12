package ru.reshaka.taskengine.infra.adapter;


import lombok.RequiredArgsConstructor;
import ru.reshaka.taskengine.domain.port.TimerPort;
import ru.reshaka.taskengine.infra.postgre.model.ResultVariantEntity;
import ru.reshaka.taskengine.infra.postgre.model.VariantEntity;
import ru.reshaka.taskengine.infra.postgre.repo.ResultVariantJpaRepository;
import ru.reshaka.taskengine.infra.postgre.repo.VariantJpaRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
public class TimerAdapter implements TimerPort {

    private final ResultVariantJpaRepository repository;
    private final VariantJpaRepository variantRepository;


    @Override
    public void startTimer(Long variantId, Long userId) {
        Optional<ResultVariantEntity> existing = repository.findByVariantIdAndUserId(variantId, userId);
        if (existing.isEmpty()) {
            ResultVariantEntity entity = ResultVariantEntity.builder()
                    .variantId(variantId)
                    .userId(userId)
                    .startedAt(Instant.now())
                    .build();
            repository.save(entity);
        }
    }

    @Override
    public void stopTimer(Long variantId, Long userId) {
        repository.findByVariantIdAndUserId(variantId, userId).ifPresent(entity -> {
            entity.setFinishedAt(Instant.now());
            if (entity.getStartedAt() != null) {
                entity.setTimeTakenSec(
                        (int) Duration.between(entity.getStartedAt(), entity.getFinishedAt()).getSeconds()
                );
            }
            repository.save(entity);
        });
    }

    @Override
    public boolean isTimeout(Long variantId, Long userId) {
        Optional<ResultVariantEntity> variantResultOpt = repository.findByVariantIdAndUserId(variantId, userId);
        if (variantResultOpt.isEmpty()) return false;

        ResultVariantEntity result = variantResultOpt.get();
        if (result.getStartedAt() == null || result.getFinishedAt() != null) return false;

        VariantEntity variant = variantRepository.findById(variantId).orElse(null);
        if (variant == null || !Boolean.TRUE.equals(variant.getIsTimed()) || variant.getTimeLimitSec() == null)
            return false;

        long elapsed = Duration.between(result.getStartedAt(), Instant.now()).getSeconds();
        return elapsed > variant.getTimeLimitSec();
    }
}
