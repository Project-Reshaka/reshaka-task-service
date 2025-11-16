package ru.reshaka.taskengine.taskgensubsystem;

public class StageException extends RuntimeException {
    public StageException(String message, Throwable cause) {
        super(message, cause);
    }
    public StageException(String message) {
        super(message);
    }
}
