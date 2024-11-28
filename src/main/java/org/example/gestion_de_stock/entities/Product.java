package org.example.gestion_de_stock.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    // Getters, setters et constructeurs

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;




}


