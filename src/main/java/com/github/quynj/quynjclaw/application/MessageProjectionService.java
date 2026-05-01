package com.github.quynj.quynjclaw.application;

import com.github.quynj.quynjclaw.dto.AgentMessageDTO;
import com.github.quynj.quynjclaw.store.LocalMessageStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageProjectionService {
    private final LocalMessageStore store;

    public MessageProjectionService(LocalMessageStore store) {
        this.store = store;
    }

    public List<AgentMessageDTO> list(String sessionId) {
        return store.listMessages(sessionId);
    }

    public AgentMessageDTO append(String sessionId, AgentMessageDTO message) {
        return store.append(sessionId, message);
    }

    public Optional<AgentMessageDTO> getMessage(String messageId) {
        return store.getMessage(messageId);
    }
}
