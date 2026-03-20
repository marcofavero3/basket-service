package com.devdog.basketservice.service;

import com.devdog.basketservice.client.PlatziStoreClient;
import com.devdog.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllProducts() {
        log.info("Getting all products");
        return platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "products", key = "#productId")
    public PlatziProductResponse getProductById(@RequestParam long productId) {
        log.info("Getting product by id {}", productId);
        return platziStoreClient.getProductById(productId);
    }
}
