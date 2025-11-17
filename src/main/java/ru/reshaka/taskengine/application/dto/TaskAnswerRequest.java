package ru.reshaka.taskengine.application.dto;

import lombok.*;
import ru.reshaka.taskengine.domain.model.TaskBaseType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskAnswerRequest {
    private Long taskId;
    private Object answer;
    private TaskBaseType baseType;
}



