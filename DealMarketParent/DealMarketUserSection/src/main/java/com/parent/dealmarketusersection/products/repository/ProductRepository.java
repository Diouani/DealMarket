package com.parent.dealmarketusersection.products.repository;

import com.root.dealmarketshared.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
