package com.example.secondmicroservice.client;


import com.example.microservice.api.model.UserDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableKafka
public class BaseController {

    @KafkaListener(topics = "user")
    public void getUser(ConsumerRecord<Long, UserDto> data){
        System.out.println(data.value());
    }
}
