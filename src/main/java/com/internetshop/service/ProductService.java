package com.internetshop.service;

import com.internetshop.model.Product;

public interface ProductService extends GenericService<Product, Long> {
    Product create(Product product);

    Product update(Product product);
}
