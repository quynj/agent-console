package com.github.quynj.quynjclaw.api;

import com.github.quynj.quynjclaw.application.ConversationService;
import com.github.quynj.quynjclaw.application.MessageProjectionService;
import com.github.quynj.quynjclaw.common.Result;
import com.github.quynj.quynjclaw.dto.AgentMessageDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final ConversationService conversationService;
    private final MessageProjectionService messageService;

    public MessageController(ConversationService conversationService, MessageProjectionService messageService) {
        this.conversationService = conversationService;
        this.messageService = messageService;
    }

    @GetMapping("/api/sessions/{sessionId}/messages")
    public Result<List<AgentMessageDTO>> list(@PathVariable String sessionId) {
        conversationService.get(sessionId);
        return Result.ok(messageService.list(sessionId));
    }

    @GetMapping("/api/messages/{messageId}")
    public Result<AgentMessageDTO> get(@PathVariable String messageId) {
        return Result.ok(messageService.getMessage(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found: " + messageId)));
    }
}
