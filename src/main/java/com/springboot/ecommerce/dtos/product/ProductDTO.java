package com.springboot.ecommerce.dtos.product;

import com.springboot.ecommerce.models.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDTO implements Serializable {
    private String title;
    private String description;
    private int price;
    private int discountPrice;
    private int discountPercent;
    private int quantity;
    private String brand;
    private String color;
    private Set<Size> sizes = new HashSet<>();
    private String imageUrl;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
}
