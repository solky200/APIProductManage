package com.codegym.ktraspringboot3.repository;

import com.codegym.ktraspringboot3.model.Category;
import com.codegym.ktraspringboot3.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Query("SELECT p FROM Product p ORDER BY p.amount ASC")
    List<Product> findAllSortedByAmount();

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> findTop3ByPriceDesc();

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategory(@Param("category") Long categoryId);
}
