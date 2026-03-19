package com.devdog.basketservice.controllers;

import com.devdog.basketservice.client.response.PlatziProductResponse;
import com.devdog.basketservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private ProductService productService;

    //endpoint para todos produtos
    @GetMapping
    public ResponseEntity<List<PlatziProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //endpoint para produto por id
    @GetMapping("/{id}")
    public ResponseEntity<PlatziProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
