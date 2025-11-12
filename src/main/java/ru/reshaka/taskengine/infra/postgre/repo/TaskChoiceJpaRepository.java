package ru.reshaka.taskengine.infra.postgre.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.reshaka.taskengine.infra.postgre.model.TaskChoiceEntity;

import java.util.List;

public interface TaskChoiceJpaRepository extends JpaRepository<TaskChoiceEntity, Long> {
    List<TaskChoiceEntity> findBySubjectId(Long subjectId);

    @Query(value = "SELECT * FROM task_choice WHERE subject_id = :subjectId ORDER BY random() LIMIT :count", nativeQuery = true)
    List<TaskChoiceEntity> findRandomBySubject(@Param("subjectId") Long subjectId, @Param("count") int count);
}
