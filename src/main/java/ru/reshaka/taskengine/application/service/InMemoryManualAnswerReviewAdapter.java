package ru.reshaka.taskengine.application.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryManualAnswerReviewAdapter implements ManualAnswerReviewPort {

    private final Map<Key, Boolean> storage = new ConcurrentHashMap<>();

    @Override
    public Boolean findReview(Long userId, Long taskId) {
        return storage.get(new Key(userId, taskId));
    }

    @Override
    public void saveReview(Long userId, Long taskId, Boolean isCorrect) {
        storage.put(new Key(userId, taskId), isCorrect);
    }

    private record Key(Long userId, Long taskId) { }
}

