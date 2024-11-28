package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}