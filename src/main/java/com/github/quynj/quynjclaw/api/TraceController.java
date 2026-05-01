package com.github.quynj.quynjclaw.api;

import com.github.quynj.quynjclaw.application.ConversationService;
import com.github.quynj.quynjclaw.common.Result;
import com.github.quynj.quynjclaw.dto.TraceSpanDTO;
import com.github.quynj.quynjclaw.store.LocalTraceStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TraceController {
    private final ConversationService conversationService;
    private final LocalTraceStore traceStore;

    public TraceController(ConversationService conversationService, LocalTraceStore traceStore) {
        this.conversationService = conversationService;
        this.traceStore = traceStore;
    }

    @GetMapping("/api/sessions/{sessionId}/traces")
    public Result<List<TraceSpanDTO>> list(@PathVariable String sessionId) {
        conversationService.get(sessionId);
        return Result.ok(traceStore.list(sessionId));
    }

    @GetMapping("/api/traces/{traceId}")
    public Result<TraceSpanDTO> get(@PathVariable String traceId) {
        return Result.ok(traceStore.get(traceId)
                .orElseThrow(() -> new IllegalArgumentException("Trace not found: " + traceId)));
    }
}
