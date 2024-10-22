package br.com.douglas.Catalogo.services;

import br.com.douglas.Catalogo.dto.CategoryDTO;
import br.com.douglas.Catalogo.entities.Category;
import br.com.douglas.Catalogo.repositories.CategoryRespository;
import br.com.douglas.Catalogo.services.services.exceptions.DataBaseException;
import br.com.douglas.Catalogo.services.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<CategoryDTO> findAllPaged(Pageable pageable){
        Page<Category> list = repository.findAll(pageable);
        return list
                .map(category -> new CategoryDTO(
                        category.getId(),
                        category.getName()
                ));

    }


    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
       Optional<Category> obj = repository.findById(id);
       Category entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not found."));

       return new CategoryDTO(entity.getId(),entity.getName());
    }


    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.name()); // Correção aqui: usar o nome do DTO para definir o nome da entidade

        repository.save(entity);

        return new CategoryDTO(entity.getId(), entity.getName());
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        // Buscar a categoria pelo ID fornecido
        Optional<Category> optionalCategory = repository.findById(id);

        // Verificar se a categoria existe
        if (optionalCategory.isEmpty()) {
            throw new EntityNotFoundException("Category not found.");
        }

        // Atualizar os campos da categoria
        Category categoryToUpdate = optionalCategory.get();
        categoryToUpdate.setName(dto.name());

        // Salvar a categoria atualizada
        repository.save(categoryToUpdate);

        // Retornar um DTO da categoria atualizada
        return new CategoryDTO(categoryToUpdate.getId(), categoryToUpdate.getName());

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found");
        }
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Referential integrity violation");
        }
    }
}
