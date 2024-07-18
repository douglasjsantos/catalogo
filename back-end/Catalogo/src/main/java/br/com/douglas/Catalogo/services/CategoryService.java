package br.com.douglas.Catalogo.services;

import br.com.douglas.Catalogo.entities.Category;
import br.com.douglas.Catalogo.repositories.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository repository;

    public List<Category> findAll(){
       return repository.findAll();
    }


}
