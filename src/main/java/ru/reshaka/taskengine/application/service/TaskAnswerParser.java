package ru.reshaka.taskengine.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.reshaka.taskengine.domain.model.*;

import java.util.List;

@RequiredArgsConstructor
public class TaskAnswerParser {

    private final ObjectMapper mapper;

    public Object parse(TaskBaseType type, Object raw) {
        return switch (type) {
            case INPUT -> parseInput(raw);
            case CHOICE -> parseChoice(raw);
            case MANUAL -> raw; // manual может быть чем угодно, отдаём как есть
        };
    }

    private String parseInput(Object raw) {
        if (raw instanceof String s) return s;
        return mapper.convertValue(raw, String.class);
    }

    private List<Integer> parseChoice(Object raw) {
        if (raw instanceof List<?> list) {
            return list.stream()
                    .map(e -> mapper.convertValue(e, Integer.class))
                    .toList();
        }
        return mapper.convertValue(raw, mapper.getTypeFactory().constructCollectionType(List.class, Integer.class));
    }
}
