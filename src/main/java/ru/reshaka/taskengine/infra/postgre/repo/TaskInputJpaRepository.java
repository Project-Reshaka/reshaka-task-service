package ru.reshaka.taskengine.infra.postgre.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.reshaka.taskengine.infra.postgre.model.TaskInputEntity;

import java.util.List;

public interface TaskInputJpaRepository extends JpaRepository<TaskInputEntity, Long> {
    List<TaskInputEntity> findBySubjectId(Long subjectId);

    @Query(value = "SELECT * FROM task_input WHERE subject_id = :subjectId ORDER BY random() LIMIT :count", nativeQuery = true)
    List<TaskInputEntity> findRandomBySubject(@Param("subjectId") Long subjectId, @Param("count") int count);
}
