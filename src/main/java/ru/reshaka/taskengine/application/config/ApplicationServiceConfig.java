package ru.reshaka.taskengine.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.reshaka.taskengine.application.service.*;
import ru.reshaka.taskengine.domain.port.TaskRepositoryPort;
import ru.reshaka.taskengine.domain.service.SingleTaskValidationService;
import ru.reshaka.taskengine.domain.service.VariantExecutionService;
import ru.reshaka.taskengine.taskgensubsystem.DefaultTaskGenerator;

@Configuration
public class ApplicationServiceConfig {

    @Bean
    public TaskAnswerParser taskAnswerParser() {
        return new TaskAnswerParser(new ObjectMapper());
    }

    @Bean
    public TaskExecutionAppService taskExecutionAppService(
            SingleTaskValidationService singleTaskValidationService,
            TaskRepositoryPort taskRepositoryPort,
            TaskAnswerParser taskAnswerParser,
            ManualAnswerReviewPort manualAnswerReviewPort) {
        return new TaskExecutionAppService(singleTaskValidationService, taskRepositoryPort, taskAnswerParser, manualAnswerReviewPort);
    }

    @Bean
    public VariantExecutionAppService variantExecutionAppService(
            VariantExecutionService variantExecutionService) {
        return new VariantExecutionAppService(variantExecutionService);
    }

    @Bean
    public TaskGenerationAppService taskGenerationAppService() {
        return new TaskGenerationAppService(new DefaultTaskGenerator());
    }
}