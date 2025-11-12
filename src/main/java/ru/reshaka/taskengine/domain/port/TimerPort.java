package ru.reshaka.taskengine.domain.port;

import java.util.UUID;

public interface TimerPort {
    void startTimer(Long variantId, Long userId);
    void stopTimer(Long variantId, Long userId);
    boolean isTimeout(Long variantId, Long userId);
}
