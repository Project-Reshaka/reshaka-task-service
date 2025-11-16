package ru.reshaka.taskengine.taskgensubsystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Variable {
    private String name;
    private VariableGenerator generator;
}
