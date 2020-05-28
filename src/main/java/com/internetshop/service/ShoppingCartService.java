package com.internetshop.service;

import com.internetshop.model.Product;
import com.internetshop.model.ShoppingCart;
import java.util.List;

public interface ShoppingCartService {
    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

    ShoppingCart create(Long userId);
}
