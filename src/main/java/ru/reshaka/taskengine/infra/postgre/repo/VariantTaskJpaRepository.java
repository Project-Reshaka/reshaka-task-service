package ru.reshaka.taskengine.infra.postgre.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.reshaka.taskengine.infra.postgre.model.VariantTaskEntity;
import java.util.List;

public interface VariantTaskJpaRepository extends JpaRepository<VariantTaskEntity, Long> {
    List<VariantTaskEntity> findByVariantId(Long variantId);
}
