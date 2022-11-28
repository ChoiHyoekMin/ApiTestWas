package com.example.demo.kafkachat.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.example.demo.kafkachat.constants.KafkaConstants;
import com.example.demo.kafkachat.model.Message;
import com.example.demo.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    // Producer
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    private MessageService messageService;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @MessageMapping("/receive/{id}")
    @SendTo("/send/{id}")
    public Message broadcastGroupMessage(@Payload Message message, @DestinationVariable String id) {
        message.setRoomId(id);
        messageService.createMessage(message);
        return message;
    }

    @MessageMapping("/init/{id}/{session}")
    @SendTo("/init/{id}/{session}")
    public List<Message> initGroupMessage(@DestinationVariable String id, @DestinationVariable String session) {
        List<Message> messages = messageService.initMessages(id);
        
        return messages;
    }
}
