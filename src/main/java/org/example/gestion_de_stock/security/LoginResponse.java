package org.example.gestion_de_stock.security;

import lombok.Getter;
import lombok.Setter;



public class LoginResponse {
    private String token;
    private long expiresIn;
    private User.Role role;


    public LoginResponse  setRole(User.Role role) {
        this.role = role;
        return this;
    }

    public User.Role getRole() {
        return role;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this; // Return 'this' for method chaining
    }


    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this; // Return 'this' for method chaining
    }




}
