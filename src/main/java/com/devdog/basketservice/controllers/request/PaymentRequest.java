package com.devdog.basketservice.controllers.request;

import com.devdog.basketservice.entity.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    private PaymentMethod paymentMethod;
}
