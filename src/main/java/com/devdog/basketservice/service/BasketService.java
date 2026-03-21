package com.devdog.basketservice.service;

import com.devdog.basketservice.client.response.PlatziProductResponse;
import com.devdog.basketservice.controllers.request.BasketRequest;
import com.devdog.basketservice.entity.Basket;
import com.devdog.basketservice.entity.Product;
import com.devdog.basketservice.entity.Status;
import com.devdog.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Basket getBasketById(String id){
        return basketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Basket not found"));
    }

    public Basket createBasket(BasketRequest basketRequest) {

        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> { throw new IllegalArgumentException(
                        "There is already basket with this client id: " + basketRequest.clientId()
                );});

        List<Product> products = new ArrayList<>();
        basketRequest.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.getProductById(productRequest.id());

            products.add( Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());

        });

        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .products(products)
                .build();

        basket.calcularTotalPrice();
        return basketRepository.save(basket);
    }

    public Basket updateBasket(String basketId, BasketRequest request) {

        Basket savedBasket = getBasketById(basketId);

        List<Product> products = new ArrayList<>();
        request.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.getProductById(productRequest.id());
            products.add( Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        });

        savedBasket.setProducts(products);
        savedBasket.calcularTotalPrice();

        return basketRepository.save(savedBasket);
    }
}
