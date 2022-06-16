package com.parent.dealmarketusersection.service;

import com.parent.dealmarketusersection.repository.CustomerRepository;
import com.root.dealmarketshared.dto.Purchase;
import com.root.dealmarketshared.dto.PurchaseResponse;
import com.root.dealmarketshared.entity.Customer;
import com.root.dealmarketshared.entity.Order;
import com.root.dealmarketshared.entity.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CheckoutService {

    private final CustomerRepository customerRepository;

    public CheckoutService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;

//        // initialize Stripe API with secret key
//        Stripe.apiKey = secretKey;
    }


    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add);

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();

        // check if this is an existing customer
        String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);

        if (customerFromDB != null) {
            // we found them ... let's assign them accordingly
            customer = customerFromDB;
        }

        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }


//    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
//
//        List<String> paymentMethodTypes = new ArrayList<>();
//        paymentMethodTypes.add("card");
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("amount", paymentInfo.getAmount());
//        params.put("currency", paymentInfo.getCurrency());
//        params.put("payment_method_types", paymentMethodTypes);
//        params.put("description", "Luv2Shop purchase");
//        params.put("receipt_email", paymentInfo.getReceiptEmail());
//
//        return PaymentIntent.create(params);
//    }
//
    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        //
        return UUID.randomUUID().toString();
    }
}
