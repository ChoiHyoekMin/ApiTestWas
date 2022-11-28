package com.example.demo.model.dao;

import java.util.List;

import com.example.demo.kafkachat.model.Message;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MessageMapper {
    List<Message> initMessages(String roomId);
    int createMessage(Message message);
}
