package com.kumcode.kart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kumcode.kart.Service.ProductService;
import com.kumcode.kart.model.ProductDTLS;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTLS> createProduct(@RequestBody ProductDTLS product) {
        ProductDTLS createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTLS>> getAllProducts() {
        List<ProductDTLS> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTLS> updateProduct(@PathVariable String id, @RequestBody ProductDTLS entity) {
        ProductDTLS updateProdut = productService.updateProduct(id, entity);
        return ResponseEntity.ok(updateProdut);
    }
 

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTLS> getProductById(@PathVariable UUID id) {
        Optional<ProductDTLS> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        Optional<ProductDTLS> product = productService.getProductById(id);
        if (product.isPresent()) {
            String details= productService.deleteProduct(id);
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
