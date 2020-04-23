package vlad.petrovskyi.internetshop.service.impl;

import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.ShoppingCart;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;

import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        return false;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {

    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return null;
    }
}
