package vlad.petrovskyi.internetshop.dao.impl;

import vlad.petrovskyi.internetshop.dao.ShoppingCartDao;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.ShoppingCart;

import java.util.List;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
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
