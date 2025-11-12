package ru.reshaka.taskengine.infra.postgre.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.reshaka.taskengine.infra.postgre.model.ResultVariantTaskEntity;

public interface ResultVariantTaskJpaRepository extends JpaRepository<ResultVariantTaskEntity, Long> {
}
