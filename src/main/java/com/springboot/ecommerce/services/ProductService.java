package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.product.ProductDTO;
import com.springboot.ecommerce.exceptions.ProductException;
import com.springboot.ecommerce.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product create(ProductDTO product);
    String delete(Long productId) throws ProductException;
    Product update(Long productId, ProductDTO product) throws ProductException;
    Product findProductById(Long productId) throws ProductException;
    List<Product> findProductByCategory(String category) throws ProductException;
    Page<Product> getAllProduct(String category,
                                List<String> color,
                                List<String> sizes,
                                Integer minPrice,
                                Integer maxPrice,
                                Integer discount,
                                String sort,
                                String stock,
                                Integer pageNumber,
                                Integer pageSize
    ) throws ProductException;
}
