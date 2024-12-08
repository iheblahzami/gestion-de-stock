package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);

    // Retrieve all users
    public List<User> getAllUsers() ;

    // Retrieve a user by ID
    public User getUserById(Long id) ;

    // Update a user's details
    public User updateUser(Long id, User userDetails) ;

    // Delete a user by ID
    public void deleteUser(Long id) ;

    // Retrieve users by role
    public List<User> getUsersByRole(User.Role role) ;

}
