package br.com.douglas.Catalogo.controller;

import br.com.douglas.Catalogo.entities.Category;
import br.com.douglas.Catalogo.entities.Pessoa;
import br.com.douglas.Catalogo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<Category>> findAll(){

        List<Category> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }





}
