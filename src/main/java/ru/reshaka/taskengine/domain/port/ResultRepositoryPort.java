package ru.reshaka.taskengine.domain.port;

import ru.reshaka.taskengine.domain.model.ResultTask;
import ru.reshaka.taskengine.domain.model.ResultVariant;
import ru.reshaka.taskengine.domain.model.ResultVariantTask;

import java.util.List;
import java.util.UUID;

public interface ResultRepositoryPort {
    void saveResultTask(ResultTask resultTask);
    void saveResultVariant(ResultVariant resultVariant, List<ResultVariantTask> taskResults);
    void saveResultVariantTask(ResultVariantTask resultVariantTask);
    List<ResultTask> findTaskResultsByUser(Long userId);
    List<ResultVariant> findVariantResultsByUser(Long userId);
}
