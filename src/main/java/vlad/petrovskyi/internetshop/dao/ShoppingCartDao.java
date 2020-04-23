package vlad.petrovskyi.internetshop.dao;

import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {
    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart); //remove all products from the shoppingCart

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);
}
