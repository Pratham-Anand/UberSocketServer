package com.example.UberSocketServer.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestRequest {

    public String name;
    private String message;
}
