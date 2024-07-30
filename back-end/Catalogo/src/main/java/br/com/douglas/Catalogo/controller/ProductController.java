package br.com.douglas.Catalogo.controller;

import br.com.douglas.Catalogo.dto.ProductDTO;
import br.com.douglas.Catalogo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){

        Page<ProductDTO> list = service.findAllPaged(pageable);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){

        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }


    @PostMapping
    public ResponseEntity<ProductDTO> insertProduct(@RequestBody ProductDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto){
        dto = service.update(id,dto);

        return ResponseEntity.ok().body(dto);
    }



}
