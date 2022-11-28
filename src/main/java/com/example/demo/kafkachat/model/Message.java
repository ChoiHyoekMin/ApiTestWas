package com.example.demo.kafkachat.model;

import lombok.Data;

@Data
public class Message {
    private String roomId;
    private String sender;
    private String content;
    private String timestamp;

    public Message() {}

    public Message(String roomId, String sender, String content) {
        this.roomId = roomId;
        this.sender = sender;
        this.content = content;
    }
}
