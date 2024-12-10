package org.example.gestion_de_stock.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN or OPERATOR

    // Implement UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert role to GrantedAuthority
        return Collections.singletonList(() -> "ROLE_" + role.name());
    }



    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement according to your application's requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement according to your application's requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement according to your application's requirements
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement according to your application's requirements
    }

    public enum Role {
        ADMIN,
        OPERATOR
    }

}
