package com.github.quynj.quynjclaw.agentscope;

import com.github.quynj.quynjclaw.config.QuynjClawProperties;
import com.github.quynj.quynjclaw.dto.ChatSessionDTO;
import io.agentscope.core.memory.InMemoryMemory;
import io.agentscope.core.memory.Memory;
import io.agentscope.core.memory.autocontext.AutoContextConfig;
import io.agentscope.core.memory.autocontext.AutoContextMemory;
import io.agentscope.core.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AgentMemoryFactory {
    private static final Logger log = LoggerFactory.getLogger(AgentMemoryFactory.class);
    private final QuynjClawProperties properties;

    public AgentMemoryFactory(QuynjClawProperties properties) {
        this.properties = properties;
    }

    public Memory createMemory(ChatSessionDTO session, Model model) {
        if (!"auto-context".equalsIgnoreCase(properties.memory.type)) {
            return new InMemoryMemory();
        }
        try {
            AutoContextConfig config = AutoContextConfig.builder()
                    .maxToken(properties.memory.maxContextTokens)
                    .msgThreshold(30)
                    .lastKeep(10)
                    .tokenRatio(0.3)
                    .build();
            return new AutoContextMemory(config, model);
        } catch (RuntimeException e) {
            if (!properties.memory.fallbackToInMemory) {
                throw e;
            }
            log.warn("AutoContextMemory initialization failed for session {}. Falling back to InMemoryMemory.", session.id, e);
            return new InMemoryMemory();
        }
    }
}
