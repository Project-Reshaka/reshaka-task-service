package ru.reshaka.taskengine.infra.adapter;

import lombok.RequiredArgsConstructor;
import ru.reshaka.taskengine.domain.model.TaskBase;
import ru.reshaka.taskengine.domain.model.TaskBaseType;
import ru.reshaka.taskengine.domain.port.TaskRepositoryPort;
import ru.reshaka.taskengine.infra.postgre.mapper.TaskMapper;
import ru.reshaka.taskengine.infra.postgre.repo.TaskChoiceJpaRepository;
import ru.reshaka.taskengine.infra.postgre.repo.TaskInputJpaRepository;
import ru.reshaka.taskengine.infra.postgre.repo.TaskManualJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final TaskChoiceJpaRepository choiceRepo;
    private final TaskInputJpaRepository inputRepo;
    private final TaskManualJpaRepository manualRepo;
    private final TaskMapper mapper;

    @Override
    public Optional<TaskBase> findById(Long taskId, TaskBaseType type) {
        return switch (type) {
            case CHOICE -> choiceRepo.findById(taskId).map(mapper::toDomain);
            case INPUT -> inputRepo.findById(taskId).map(mapper::toDomain);
            case MANUAL -> manualRepo.findById(taskId).map(mapper::toDomain);
        };
    }

    @Override
    public List<TaskBase> findBySubject(Long subjectId) {
        List<TaskBase> result = new ArrayList<>();
        result.addAll(choiceRepo.findBySubjectId(subjectId).stream().map(mapper::toDomain).toList());
        result.addAll(inputRepo.findBySubjectId(subjectId).stream().map(mapper::toDomain).toList());
        result.addAll(manualRepo.findBySubjectId(subjectId).stream().map(mapper::toDomain).toList());
        return result;
    }

    @Override
    public List<TaskBase> findRandom(Long subjectId, int count) {
        int perType = Math.max(1, count / 3);

        List<TaskBase> result = new ArrayList<>();
        result.addAll(choiceRepo.findRandomBySubject(subjectId, perType).stream().map(mapper::toDomain).toList());
        result.addAll(inputRepo.findRandomBySubject(subjectId, perType).stream().map(mapper::toDomain).toList());
        result.addAll(manualRepo.findRandomBySubject(subjectId, perType).stream().map(mapper::toDomain).toList());

        return result;
    }
}

