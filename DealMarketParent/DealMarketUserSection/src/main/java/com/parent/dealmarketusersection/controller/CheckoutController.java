package com.parent.dealmarketusersection.controller;

import com.parent.dealmarketusersection.service.CheckoutService;
import com.root.dealmarketshared.dto.Purchase;
import com.root.dealmarketshared.dto.PurchaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        return checkoutService.placeOrder(purchase);
    }

//    @PostMapping("/payment-intent")
//    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {
//
//        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
//
//        String paymentStr = paymentIntent.toJson();
//
//        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
//    }
}
