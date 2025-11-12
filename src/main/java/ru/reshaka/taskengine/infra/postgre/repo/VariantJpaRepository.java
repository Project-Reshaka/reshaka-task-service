package ru.reshaka.taskengine.infra.postgre.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.reshaka.taskengine.infra.postgre.model.VariantEntity;

import java.util.List;

public interface VariantJpaRepository extends JpaRepository<VariantEntity, Long> {
    List<VariantEntity> findBySubjectId(Long subjectId);

}

