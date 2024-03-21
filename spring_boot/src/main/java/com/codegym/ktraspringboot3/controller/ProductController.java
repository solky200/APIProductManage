package com.codegym.ktraspringboot3.controller;

import com.codegym.ktraspringboot3.model.Product;
import com.codegym.ktraspringboot3.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductRepository iProductRepository;
    @GetMapping
    public ResponseEntity<Iterable<Product>> findAllCustomer() {
        List<Product> customers = (List<Product>) iProductRepository.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findCustomerById(@PathVariable Long id) {
        Optional<Product> customerOptional = iProductRepository.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveCustomer(@RequestBody Product customer) {
        return new ResponseEntity<>(iProductRepository.save(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateCustomer(@PathVariable Long id, @RequestBody Product customer) {
        Optional<Product> customerOptional = iProductRepository.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setId(customerOptional.get().getId());
        return new ResponseEntity<>(iProductRepository.save(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteCustomer(@PathVariable Long id) {
        Optional<Product> customerOptional = iProductRepository.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iProductRepository.deleteById(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }
    // API endpoint để tìm kiếm theo khoảng giá
    @GetMapping("/searchByPriceRange")
    public ResponseEntity<List<Product>> searchByPriceRange(
            @RequestParam("minPrice") double minPrice,
            @RequestParam("maxPrice") double maxPrice
    ) {
        List<Product> products = iProductRepository.findByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    // API endpoint để sắp xếp theo số lượng
    @GetMapping("/sortByAmount")
    public ResponseEntity<List<Product>> sortByAmount() {
        List<Product> products = iProductRepository.findAllSortedByAmount();
        return ResponseEntity.ok(products);
    }

    // API endpoint để lấy top 3 sản phẩm có giá cao nhất
    @GetMapping("/topPricedProducts")
    public ResponseEntity<List<Product>> getTopPricedProducts() {
        List<Product> products = iProductRepository.findTop3ByPriceDesc();
        return ResponseEntity.ok(products);
    }

    // API endpoint để tìm kiếm các sản phẩm theo thể loại
    @GetMapping("/searchByCategory")
    public ResponseEntity<List<Product>> searchByCategory(
            @RequestParam("categoryId") Long categoryId
    ) {
        List<Product> products = iProductRepository.findByCategory(categoryId);
        return ResponseEntity.ok(products);
    }
}
