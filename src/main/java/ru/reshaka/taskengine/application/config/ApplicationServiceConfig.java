package ru.reshaka.taskengine.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.reshaka.taskengine.application.service.*;
import ru.reshaka.taskengine.domain.port.TaskRepositoryPort;
import ru.reshaka.taskengine.domain.service.SingleTaskValidationService;
import ru.reshaka.taskengine.domain.service.VariantExecutionService;
import ru.reshaka.taskengine.taskgensubsystem.DefaultTaskGenerator;

@Configuration
@RequiredArgsConstructor
public class ApplicationServiceConfig {

    private final SingleTaskValidationService singleTaskValidationService;

    private final TaskRepositoryPort taskRepositoryPort;

    private final TaskAnswerParser taskAnswerParser;

    private final ManualAnswerReviewPort manualAnswerReviewPort;

    private final VariantExecutionService variantExecutionService;

    public @Bean TaskAnswerParser taskAnswerParser() {
        return new TaskAnswerParser(new ObjectMapper());
    }

    public @Bean TaskExecutionAppService taskExecutionAppService() {
        return new TaskExecutionAppService(singleTaskValidationService, taskRepositoryPort, taskAnswerParser, manualAnswerReviewPort);
    }

    public @Bean VariantExecutionAppService variantExecutionAppService() {
        return new VariantExecutionAppService(variantExecutionService);
    }

    public @Bean TaskGenerationAppService taskGenerationAppService() {
        return new TaskGenerationAppService(new DefaultTaskGenerator());
    }

}
