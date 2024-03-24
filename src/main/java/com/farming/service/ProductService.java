package com.farming.service;

import org.springframework.stereotype.Service;

import com.farming.models.Product;
import com.farming.models.User;

import java.util.List;

@Service
public interface ProductService {
    public Product saveProduct(Product product);
    public List<Product> allProducts(String email);
    public List<Product> allProducts();
    public void deleteProduct(Integer pid);
    public void lessQuantity(Integer pid,Integer quantity);
    public void addQuantity(Integer pid,Integer quantity);
}
