package com.example.UberSocketServer.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {

    private Long passengerId;

    private Long bookingId;

//    private ExactLocation startLocation;
//
//    private ExactLocation endLocation;

    private List<Long> driverIds;


}
