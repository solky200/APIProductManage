package com.codegym.ktraspringboot3.service.impl;

import com.codegym.ktraspringboot3.model.Category;
import com.codegym.ktraspringboot3.model.Product;
import com.codegym.ktraspringboot3.repository.ICategoryRepository;
import com.codegym.ktraspringboot3.repository.IProductRepository;
import com.codegym.ktraspringboot3.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public Iterable<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        return iProductRepository.findByPriceRange(minPrice, maxPrice);
    }

    @Override
    public List<Product> findAllSortedByAmount() {
        return iProductRepository.findAllSortedByAmount();
    }

    @Override
    public List<Product> findTop3ByPriceDesc() {
        List<Product> products = iProductRepository.findTop3ByPriceDesc();
        if (3 < products.size()) {
            return products.subList(0, 3);
        } else {
            return products;
        }
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {
        return iProductRepository.findByCategory(categoryId);
    }

}
