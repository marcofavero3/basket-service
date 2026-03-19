package com.devdog.basketservice.service;

import com.devdog.basketservice.client.PlatziStoreClient;
import com.devdog.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    //preciso conectar com a api externa

    private final PlatziStoreClient platziStoreClient;

    public List<PlatziProductResponse> getAllProducts() {
        return platziStoreClient.getAllProducts();
    }

    public PlatziProductResponse getProductById(@RequestParam long id) {
        return platziStoreClient.getProductById(id);
    }
}
