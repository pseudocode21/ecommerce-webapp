package com.app.ecom.repository;

import com.app.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();

    @Query("SELECT p from products p where p.active = true and p.StockQuantity>0 and LOWER(p.name) like lower(concat('%',:keyword,'%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);

}

