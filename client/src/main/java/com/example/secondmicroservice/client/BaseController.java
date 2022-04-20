package com.example.secondmicroservice.client;


import com.example.microservice.api.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableKafka
@Slf4j
@RestController
public class BaseController {

    @KafkaListener(topics = "user")
    public void getUser(ConsumerRecord<Long, UserDto> data){
        log.info("Receive user: " + data.value());
    }

    @KafkaListener(topics = "userList")
    public void getAllUsers(ConsumerRecord<Long, List<UserDto>> data){
        log.info("Receive user list: " + data.value());
    }
}


