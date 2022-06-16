package com.parent.dealmarketusersection.repository;

import com.root.dealmarketshared.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product , Long> {

    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

    @Query("select p from Product p where upper(p.name) like upper(concat('%', ?1, '%'))")
    Page<Product> findByNameContainingIgnoreCase(@RequestParam("name") String name, Pageable pageable);

}
