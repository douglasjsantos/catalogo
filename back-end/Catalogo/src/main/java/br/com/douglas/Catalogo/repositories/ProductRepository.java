package br.com.douglas.Catalogo.repositories;

import br.com.douglas.Catalogo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
