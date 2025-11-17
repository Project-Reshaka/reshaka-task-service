package ru.reshaka.taskengine.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariantStartResponse {
    private Long variantId;
    private Long userId;
    private long startTimestamp;
}
