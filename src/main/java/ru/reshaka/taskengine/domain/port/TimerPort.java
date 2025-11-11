package ru.reshaka.taskengine.domain.port;

import java.util.UUID;

public interface TimerPort {
    void startTimer(UUID variantId, UUID userId);
    void stopTimer(UUID variantId, UUID userId);
    boolean isTimeout(UUID variantId, UUID userId);
}
