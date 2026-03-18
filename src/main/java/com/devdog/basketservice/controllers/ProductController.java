package com.devdog.basketservice.controllers;

import com.devdog.basketservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private ProductService productService;

    //endpoint para todos produtos
    @GetMapping
    public ResponseEntity<Void> getAllProducts() {
        return ResponseEntity.ok().build();
    }

    //endpoint para produto por id
    @GetMapping("/{id}")
    public ResponseEntity<Void> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
