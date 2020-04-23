package vlad.petrovskyi.internetshop.service;

import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart); //remove all products from the shoppingCart

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);
}
