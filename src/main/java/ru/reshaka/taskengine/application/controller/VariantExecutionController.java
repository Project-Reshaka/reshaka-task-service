package ru.reshaka.taskengine.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.reshaka.taskengine.application.dto.*;
import ru.reshaka.taskengine.application.service.VariantExecutionAppService;

@RestController
@RequestMapping("/variants")
@RequiredArgsConstructor
public class VariantExecutionController {

    private final VariantExecutionAppService variantService;

    @PostMapping("/{variantId}/start")
    public VariantStartResponse startVariant(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long variantId
    ) {
        VariantStartRequest request = new VariantStartRequest();
        request.setVariantId(variantId);
        return variantService.start(userId, request);
    }

    @PostMapping("/{variantId}/finish")
    public VariantFinishResponse finishVariant(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long variantId,
            @RequestBody VariantFinishRequest requestBody
    ) {
        requestBody.setVariantId(variantId);
        return variantService.finish(userId, requestBody);
    }
}
