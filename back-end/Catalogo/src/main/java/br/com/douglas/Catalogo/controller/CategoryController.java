package br.com.douglas.Catalogo.controller;

import br.com.douglas.Catalogo.dto.CategoryDTO;
import br.com.douglas.Catalogo.entities.Category;
import br.com.douglas.Catalogo.entities.Pessoa;
import br.com.douglas.Catalogo.services.CategoryService;
import br.com.douglas.Catalogo.services.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;


    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){

        List<CategoryDTO> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){

        CategoryDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }






}
