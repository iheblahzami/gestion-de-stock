package org.example.gestion_de_stock.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {

    private String email;

    private String password;

    private String fullName;
}
