package ru.reshaka.taskengine.application.service;

public interface ManualAnswerReviewPort {
    Boolean findReview(Long userId, Long taskId);
    void saveReview(Long userId, Long taskId, Boolean isCorrect);
}
