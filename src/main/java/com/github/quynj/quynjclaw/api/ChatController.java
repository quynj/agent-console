package com.github.quynj.quynjclaw.api;

import com.github.quynj.quynjclaw.application.AgentScopeRuntimeService;
import com.github.quynj.quynjclaw.common.Result;
import com.github.quynj.quynjclaw.dto.ChatResponse;
import com.github.quynj.quynjclaw.dto.SendMessageRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions/{sessionId}/messages")
public class ChatController {
    private final AgentScopeRuntimeService runtimeService;

    public ChatController(AgentScopeRuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping
    public Result<ChatResponse> send(@PathVariable String sessionId, @Valid @RequestBody SendMessageRequest request) {
        return Result.ok(runtimeService.chat(sessionId, request));
    }
}
