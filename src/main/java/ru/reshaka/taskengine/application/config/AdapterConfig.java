package ru.reshaka.taskengine.application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.reshaka.taskengine.domain.port.ResultRepositoryPort;
import ru.reshaka.taskengine.domain.port.TaskRepositoryPort;
import ru.reshaka.taskengine.domain.port.TimerPort;
import ru.reshaka.taskengine.infra.adapter.*;
import ru.reshaka.taskengine.infra.postgre.mapper.ResultMapper;
import ru.reshaka.taskengine.infra.postgre.mapper.TaskMapper;
import ru.reshaka.taskengine.infra.postgre.mapper.VariantTaskMapper;
import ru.reshaka.taskengine.infra.postgre.repo.*;

@Configuration
@RequiredArgsConstructor
public class AdapterConfig {

    private final ResultTaskJpaRepository resultTaskJpaRepository;

    private final ResultVariantJpaRepository resultVariantJpaRepository;

    private final ResultVariantTaskJpaRepository resultVariantTaskJpaRepository;

    private final TaskChoiceJpaRepository taskChoiceJpaRepository;

    private final TaskInputJpaRepository taskInputJpaRepository;

    private final TaskManualJpaRepository taskManualJpaRepository;

    private final VariantJpaRepository variantJpaRepository;

    private final VariantTaskJpaRepository variantTaskJpaRepository;


    public @Bean ResultRepositoryPort resultRepository() {
        return new ResultRepositoryAdapter(resultTaskJpaRepository,
                resultVariantJpaRepository,
                resultVariantTaskJpaRepository,
                new ResultMapper());
    }

    public @Bean TaskMapper taskMapper(@Autowired ManualTaskValidatorFactory manualTaskValidatorFactory) {
        return new TaskMapper(manualTaskValidatorFactory);
    }

    public @Bean TaskRepositoryPort taskRepository(@Autowired TaskMapper taskMapper) {
        return new TaskRepositoryAdapter(taskChoiceJpaRepository, taskInputJpaRepository, taskManualJpaRepository,
               taskMapper);
    }

    public @Bean TimerPort timer() {
        return new TimerAdapter(resultVariantJpaRepository, variantJpaRepository);
    }

    public @Bean VariantRepositoryAdapter variantRepository() {
        return new VariantRepositoryAdapter(variantJpaRepository, variantTaskJpaRepository, new VariantTaskMapper());
    }
}
