//package com.example.UberSocketServer.controller;
//
//
//import com.example.UberSocketServer.dto.ChatRequest;
//import com.example.UberSocketServer.dto.ChatResponse;
//import com.example.UberSocketServer.dto.TestRequest;
//import com.example.UberSocketServer.dto.TestResponse;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.scheduling.annotation.Scheduled;
//
//
//@Controller
//public class TestController {
//
//    private final SimpMessagingTemplate simpMessagingTemplate;
//
//    public TestController(SimpMessagingTemplate simpMessagingTemplate) {
//        this.simpMessagingTemplate = simpMessagingTemplate;
//    }
//
////    @MessageMapping("/ping")     //client will send to /app/ping
////    @SendTo("/topic/ping")    //server will send to /topic/ping
////    public TestResponse pingCheck(TestRequest message) {
////        System.out.println("Received message from client " + message.getName());
////        return TestResponse.builder().data("Received").build();
////    }
//
////    @Scheduled(fixedDelay = 2000)    //this will periodically send message to client in every 2 sec.
////
////    public void sendMessageToClient(){
////        System.out.println("Message sent to client");
////        simpMessagingTemplate.convertAndSend("/topic/chat","Message sent from server "+System.currentTimeMillis());
////    }\
//
//
//    @MessageMapping("/chat/{room}")
//    @SendTo("/topic/message/{room}")
//    public ChatResponse chatMessage(@DestinationVariable("room") String room, ChatRequest request) {
//        return ChatResponse.builder()
//                .name(request.getName())
//                .message(request.getMessage())
//                .timeStamp("" + System.currentTimeMillis())
//                .build();
//    }
//
//    }
//
//
//
