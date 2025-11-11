package ru.reshaka.taskengine.domain.port;

import ru.reshaka.taskengine.domain.model.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {
    Optional<TaskBase> findById(UUID taskId, TaskBaseType type);
    List<TaskBase> findBySubject(UUID subjectId);
    List<TaskBase> findRandom(UUID subjectId, int count);
}

