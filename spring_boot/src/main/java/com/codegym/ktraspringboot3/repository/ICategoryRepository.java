package com.codegym.ktraspringboot3.repository;

import com.codegym.ktraspringboot3.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
