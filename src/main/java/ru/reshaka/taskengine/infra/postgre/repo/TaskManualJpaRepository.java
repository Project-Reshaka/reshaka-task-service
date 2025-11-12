package ru.reshaka.taskengine.infra.postgre.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.reshaka.taskengine.infra.postgre.model.TaskChoiceEntity;
import ru.reshaka.taskengine.infra.postgre.model.TaskInputEntity;
import ru.reshaka.taskengine.infra.postgre.model.TaskManualEntity;

import java.util.List;

public interface TaskManualJpaRepository extends JpaRepository<TaskManualEntity, Long> {
    List<TaskManualEntity> findBySubjectId(Long subjectId);

    @Query(value = "SELECT * FROM task_manual WHERE subject_id = :subjectId ORDER BY random() LIMIT :count", nativeQuery = true)
    List<TaskManualEntity> findRandomBySubject(@Param("subjectId") Long subjectId, @Param("count") int count);
}
