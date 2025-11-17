package ru.reshaka.taskengine.application.dto;

import lombok.Data;

import java.util.Map;

@Data
public class VariantFinishRequest {
    private Long variantId;
    private Map<Long, Object> answers;
}
