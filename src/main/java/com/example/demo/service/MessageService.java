package com.example.demo.service;

import java.util.List;

import com.example.demo.kafkachat.model.Message;

public interface MessageService {
    public List<Message> initMessages(String roomId);
    public int createMessage(Message message);        
}
