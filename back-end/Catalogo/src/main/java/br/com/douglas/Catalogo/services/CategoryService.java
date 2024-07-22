package br.com.douglas.Catalogo.services;

import br.com.douglas.Catalogo.dto.CategoryDTO;
import br.com.douglas.Catalogo.entities.Category;
import br.com.douglas.Catalogo.entities.Pessoa;
import br.com.douglas.Catalogo.repositories.CategoryRespository;
import br.com.douglas.Catalogo.services.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository repository;

    // Transactional props do bd, ACID ou faz tudo ou faz nd etc
    // readOnly não da looking no banco de dados (leitura sempre coloque)
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(category -> new CategoryDTO(
                        category.getId(),
                        category.getName()
                )).collect(Collectors.toList());

    }


    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
       Optional<Category> obj = repository.findById(id);
       Category entity = obj.orElseThrow(()-> new EntityNotFoundException("Entity not found."));

       return new CategoryDTO(entity.getId(),entity.getName());
    }


}
