package ru.reshaka.taskengine.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.reshaka.taskengine.application.service.InMemoryManualAnswerReviewAdapter;
import ru.reshaka.taskengine.application.service.ManualAnswerReviewPort;
import ru.reshaka.taskengine.application.service.StorageManualTaskValidatorFactory;
import ru.reshaka.taskengine.domain.port.ResultRepositoryPort;
import ru.reshaka.taskengine.domain.port.TaskRepositoryPort;
import ru.reshaka.taskengine.domain.service.SingleTaskValidationService;
import ru.reshaka.taskengine.infra.adapter.ManualTaskValidatorFactory;

@Configuration
public class TaskValidationConfig {

    public @Bean ManualAnswerReviewPort manualAnswerReviewPort() {
        return new InMemoryManualAnswerReviewAdapter();
    }

    public @Bean ManualTaskValidatorFactory manualTaskValidatorFactory(@Autowired ManualAnswerReviewPort manualAnswerReviewPort) {
        return new StorageManualTaskValidatorFactory(manualAnswerReviewPort);
    }

    public @Bean SingleTaskValidationService singleTaskValidationService(@Autowired ResultRepositoryPort resultRepositoryPort,
                                                                         @Autowired TaskRepositoryPort taskRepositoryPort) {
        return new SingleTaskValidationService(resultRepositoryPort, taskRepositoryPort);
    }

}
