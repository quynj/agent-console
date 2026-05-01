package com.github.quynj.quynjclaw.dto;

public class ChatResponse {
    public AgentMessageDTO message;

    public static ChatResponse of(AgentMessageDTO message) {
        ChatResponse response = new ChatResponse();
        response.message = message;
        return response;
    }
}
