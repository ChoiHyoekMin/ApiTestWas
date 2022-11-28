package com.example.demo.kafkachat.controller;

import com.example.demo.kafkachat.constants.KafkaConstants;
import com.example.demo.kafkachat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    // Consumer
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(
        topics = KafkaConstants.KAFKA_TOPIC,
        groupId = KafkaConstants.GROUP_ID
    )

    public void listen(Message message) {
        System.out.println("sending via kafak listener...");
        template.convertAndSend("/topic/group", message);
    }
}
