package it.epicode.capestone_backend02.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
