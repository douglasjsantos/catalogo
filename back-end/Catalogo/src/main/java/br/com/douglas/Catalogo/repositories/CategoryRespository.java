package br.com.douglas.Catalogo.repositories;

import br.com.douglas.Catalogo.dto.CategoryDTO;
import br.com.douglas.Catalogo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRespository extends JpaRepository<Category, Long> {




}
