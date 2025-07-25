package com.example.UberSocketServer.dto;

import com.example.UberEntityService.models.BookingStatus;
import com.example.UberEntityService.models.Driver;

import java.util.Optional;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingResponseDto {

    private Long bookingId;
    private BookingStatus status;
    private DriverDto driver;


}
