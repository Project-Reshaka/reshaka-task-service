package ru.reshaka.taskengine.infra.postgre.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.reshaka.taskengine.infra.postgre.model.ResultTaskEntity;

import java.util.List;

public interface ResultTaskJpaRepository extends JpaRepository<ResultTaskEntity, Long> {
    List<ResultTaskEntity> findByUserId(Long userId);
}

