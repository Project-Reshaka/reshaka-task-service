package ru.reshaka.taskengine.domain.model;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Variant {
    private Long id;
    private Long subjectId;
    private Long authorId;
    private String name;
    private String description;
    private boolean hasTimer;
    private Long timerLimitSeconds;
    private List<VariantTask> tasks;

    public int calculateScore() {
        if (tasks == null) return 0;
        return (int) tasks.stream()
                .filter(t -> Boolean.TRUE.equals(t.getIsCorrect()))
                .count();
    }
}

