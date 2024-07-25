package br.com.douglas.Catalogo.services;

import br.com.douglas.Catalogo.dto.ProductDTO;
import br.com.douglas.Catalogo.entities.Product;
import br.com.douglas.Catalogo.repositories.ProductRepository;
import br.com.douglas.Catalogo.services.services.exceptions.DataBaseException;
import br.com.douglas.Catalogo.services.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // Transactional props do bd, ACID ou faz tudo ou faz nd etc
    // readOnly não da looking no banco de dados (leitura sempre coloque)
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<Product> list = repository.findAll(pageRequest);
        return list.map(product -> new ProductDTO(product));

    }


    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
       /* Optional<Product> obj = repository.findById(id);
       Product entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not found."));

       return new ProductDTO(entity.getId(),entity.getName(),entity.getDescription(),entity.getPrice(),entity.getImgUrl(),entity.getDate());

        */

        Optional<Product> optionalProduct = repository.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new ResourceNotFoundException("Product not found");
        }
        Product product = optionalProduct.get();

        // Se estiver usando FetchType.LAZY para as categorias, você pode precisar inicializá-las aqui
        // Hibernate.initialize(product.getCategories());

        return new ProductDTO(product,product.getCategories());
    }


    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        entity.setName(dto.getName()); // Correção aqui: usar o nome do DTO para definir o nome da entidade

        repository.save(entity);

        return new ProductDTO(entity.getId(), entity.getName(),entity.getDescription(),entity.getPrice(),entity.getImgUrl(),entity.getDate());
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        // Buscar a categoria pelo ID fornecido
        Optional<Product> optionalProduct = repository.findById(id);

        // Verificar se a categoria existe
        if (optionalProduct.isEmpty()) {
            throw new EntityNotFoundException("Product not found.");
        }

        // Atualizar os campos da categoria
        Product ProductToUpdate = optionalProduct.get();
        ProductToUpdate.setName(dto.getName());

        // Salvar a categoria atualizada
        repository.save(ProductToUpdate);

        // Retornar um DTO da categoria atualizada
        return new ProductDTO(ProductToUpdate.getId(),ProductToUpdate.getName(),ProductToUpdate.getDescription(),ProductToUpdate.getPrice(),ProductToUpdate.getImgUrl(),ProductToUpdate.getDate());

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
