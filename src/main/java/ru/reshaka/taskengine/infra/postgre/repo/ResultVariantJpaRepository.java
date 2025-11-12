package ru.reshaka.taskengine.infra.postgre.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.reshaka.taskengine.infra.postgre.model.ResultVariantEntity;

import java.util.List;
import java.util.Optional;

public interface ResultVariantJpaRepository extends JpaRepository<ResultVariantEntity, Long> {
    List<ResultVariantEntity> findByUserId(Long userId);
    Optional<ResultVariantEntity> findByVariantIdAndUserId(Long variantId, Long userId);

}
