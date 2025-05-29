package it.epicode.capestone_backend02.auth;


import lombok.Data;

@Data

public class RegisterRequest {
    String name;
    String surname;
    String email;
    String username;
    String password;


}
