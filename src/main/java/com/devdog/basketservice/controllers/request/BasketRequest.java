package com.devdog.basketservice.controllers.request;

import java.util.List;

public record BasketRequest(Long clientId, List<ProductRequest> products) {
}
