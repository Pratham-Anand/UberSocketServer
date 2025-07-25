package com.example.UberSocketServer.controller;

import com.example.UberSocketServer.dto.RideRequestDto;
import com.example.UberSocketServer.dto.RideResponseDto;
import com.example.UberSocketServer.dto.UpdateBookingRequestDto;
import com.example.UberSocketServer.dto.UpdateBookingResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/socket")
public class DriverRequestController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RestTemplate restTemplate;

    public DriverRequestController(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate=simpMessagingTemplate;
        this.restTemplate=new RestTemplate();
    }


    @PostMapping("/newride")  //received from the booking service
//    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<Boolean> raiseRideRequest(@RequestBody RideRequestDto rideRequestDto){
        System.out.println("New ride request received");

        sendDriversNewRideRequest(rideRequestDto);

        //Todo : send the request to only the nearby drivers and not all drivers.

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    public void sendDriversNewRideRequest(RideRequestDto requestDto){  //socket server sending new ride request to the driver clients
        System.out.println("Sending the new ride request to the nearby drivers");

        simpMessagingTemplate.convertAndSend("/topic/rideRequest",requestDto);

    }

    @MessageMapping("/rideResponse/{userId}")     //response coming from the driver client.
    public synchronized void rideResponseHandler(@DestinationVariable("userId") String userId,   RideResponseDto rideResponseDto){
        System.out.println(rideResponseDto.getResponse() + " "+ userId);
        UpdateBookingRequestDto requestDto=UpdateBookingRequestDto.builder()
                .driverId(Optional.ofNullable(rideResponseDto.getDriverId()))
                .status("SCHEDULED")
                .bookingId(rideResponseDto.getBookingId())
                .build();

        System.out.println("Ride accepted by a driver");
        System.out.println("Booking id " + rideResponseDto.getBookingId());

        // api request to booking service to update the booking status
        ResponseEntity<UpdateBookingResponseDto> result = this.restTemplate.postForEntity("http://localhost:8086/api/v1/booking/" + rideResponseDto.bookingId, requestDto, UpdateBookingResponseDto.class);
//        System.out.println(result.getStatusCode());

        System.out.println("Sent update booking status request to booking service");

    }

}
