package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.kafkachat.model.Message;
import com.example.demo.model.dao.MessageMapper;
import com.example.demo.service.MessageService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageMapper messageMapper;

    @Override
    public List<Message> initMessages(String roomId) {
        return messageMapper.initMessages(roomId);
    }

    @Override
    public int createMessage(Message message) {
        return messageMapper.createMessage(message);
    }
}
