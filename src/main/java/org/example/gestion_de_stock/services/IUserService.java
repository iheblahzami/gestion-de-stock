package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.User;

import java.util.List;

public interface IUserService {
     User createUser(User user);

    // Retrieve all users
     List<User> getAllUsers() ;

    // Retrieve a user by ID
     User getUserById(Long id) ;

    // Update a user's details
     User updateUser(Long id, User userDetails) ;

    // Delete a user by ID
     void deleteUser(Long id) ;

    // Retrieve users by role
     List<User> getUsersByRole(User.Role role) ;
    // Authenticate a user (example: login functionality)
     User authenticateUser(String username, String password) ;
}
