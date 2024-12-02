package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByNameContainingIgnoreCase (String name);
}