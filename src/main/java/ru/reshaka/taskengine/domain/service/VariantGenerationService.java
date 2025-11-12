package ru.reshaka.taskengine.domain.service;

import ru.reshaka.taskengine.domain.model.Variant;
import ru.reshaka.taskengine.domain.port.TaskRepositoryPort;

import java.util.UUID;

public abstract class VariantGenerationService {

    protected final TaskRepositoryPort taskRepository;

    protected VariantGenerationService(TaskRepositoryPort taskRepository) {
        this.taskRepository = taskRepository;
    }

    public abstract Variant generate(Long subjectId, int numberOfTasks);
}
