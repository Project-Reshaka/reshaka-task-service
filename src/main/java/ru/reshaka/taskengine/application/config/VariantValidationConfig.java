package ru.reshaka.taskengine.application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.reshaka.taskengine.domain.port.ResultRepositoryPort;
import ru.reshaka.taskengine.domain.port.TaskRepositoryPort;
import ru.reshaka.taskengine.domain.port.TimerPort;
import ru.reshaka.taskengine.domain.port.VariantRepositoryPort;
import ru.reshaka.taskengine.domain.service.SingleTaskValidationService;
import ru.reshaka.taskengine.domain.service.VariantExecutionService;
import ru.reshaka.taskengine.infra.adapter.VariantRepositoryAdapter;

@Configuration
@RequiredArgsConstructor
public class VariantValidationConfig {

    private final TimerPort timerPort;

    private final VariantRepositoryPort variantRepository;

    private final ResultRepositoryPort resultRepository;

    private final SingleTaskValidationService singleTaskValidationService;

    public @Bean VariantExecutionService variantExecutionService() {
        return new VariantExecutionService(variantRepository, resultRepository, timerPort, singleTaskValidationService);
    }
}
