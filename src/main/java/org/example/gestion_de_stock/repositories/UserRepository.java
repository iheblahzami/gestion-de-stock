package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}