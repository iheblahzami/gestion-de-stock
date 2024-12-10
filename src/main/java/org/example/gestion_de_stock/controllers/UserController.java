package org.example.gestion_de_stock.controllers;

import org.example.gestion_de_stock.entities.AuthenticationResponse;
import org.example.gestion_de_stock.entities.User;
import org.example.gestion_de_stock.services.IUserService;
import org.example.gestion_de_stock.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

        @Autowired
        private IUserService userService;
        @Autowired
        private JwtService jwtService;

        // Create or register a new user
        @PostMapping
        public ResponseEntity<User> createUser(@RequestBody User user) {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        }

        // Retrieve all users
        @GetMapping
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }

        // Retrieve a user by ID
        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }

        // Update a user's details
        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        }

        // Delete a user by ID
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }

        // Retrieve users by role
        @GetMapping("/role")
        public ResponseEntity<List<User>> getUsersByRole(@RequestParam User.Role role) {
            List<User> users = userService.getUsersByRole(role);
            return ResponseEntity.ok(users);
        }

        // Authenticate a user (example: login functionality)
        @PostMapping("/authenticate")
        public ResponseEntity<?> authenticateUser(@RequestParam String username, @RequestParam String password) {
            User authenticatedUser = userService.authenticateUser(username, password);

            // Generate a JWT token for the authenticated user
            String token = jwtService.generateToken(authenticatedUser);

            // Return the token instead of the user object
            return ResponseEntity.ok(new AuthenticationResponse(token));
        }


}
