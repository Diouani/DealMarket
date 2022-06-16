package com.root.dealmarketshared.dto;

import com.root.dealmarketshared.entity.Address;
import com.root.dealmarketshared.entity.Customer;
import com.root.dealmarketshared.entity.Order;
import com.root.dealmarketshared.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;



@Getter
@Setter
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
