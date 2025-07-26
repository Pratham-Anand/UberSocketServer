package com.example.UberSocketServer.Consumers;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

   @KafkaListener(topics="sample-topic",groupId = "sample-group")
    public void listen(String message){
       System.out.println("Kafka message from sample-topic : "+message);
   }
}
