package com.springboot.ecommerce.services.impl;

import com.springboot.ecommerce.dtos.product.ProductDTO;
import com.springboot.ecommerce.exceptions.ProductException;
import com.springboot.ecommerce.models.Product;
import com.springboot.ecommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product create(ProductDTO product) {
        return null;
    }

    @Override
    public String delete(Long productId) throws ProductException {
        return "";
    }

    @Override
    public Product update(Long productId, ProductDTO product) throws ProductException {
        return null;
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        return null;
    }

    @Override
    public List<Product> findProductByCategory(String category) throws ProductException {
        return List.of();
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> color, List<String> sizes, Integer minPrice, Integer maxPrice, Integer discount, String sort, String stock, Integer pageNumber, Integer pageSize) throws ProductException {
        return null;
    }
}
