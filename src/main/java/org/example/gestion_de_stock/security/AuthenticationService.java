package org.example.gestion_de_stock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.management.relation.Role;
@Service
public class AuthenticationService {
    @Autowired
    private  UserRepository userRepository;
@Autowired
    private  PasswordEncoder passwordEncoder;
@Autowired
    private  AuthenticationManager authenticationManager;


//singup method
    public User signup(RegisterUserDto input) {
        User user = new User()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(input.getRole() != null ? input.getRole() : User.Role.OPERATOR); // Default to OPERATOR

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }
}
