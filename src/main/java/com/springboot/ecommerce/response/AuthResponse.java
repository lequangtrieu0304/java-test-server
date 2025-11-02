package com.springboot.ecommerce.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class AuthResponse {
    private String jwt;
    private String message;

    public AuthResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }
}
