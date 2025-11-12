package ru.reshaka.taskengine.domain.port;

import ru.reshaka.taskengine.domain.model.*;
import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {
    Optional<TaskBase> findById(Long taskId, TaskBaseType type);
    List<TaskBase> findBySubject(Long subjectId);
    List<TaskBase> findRandom(Long subjectId, int count);
}

