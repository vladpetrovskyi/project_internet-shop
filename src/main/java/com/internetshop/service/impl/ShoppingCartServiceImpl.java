package com.internetshop.service.impl;

import com.internetshop.dao.ShoppingCartDao;
import com.internetshop.dao.UserDao;
import com.internetshop.lib.Inject;
import com.internetshop.lib.Service;
import com.internetshop.model.Product;
import com.internetshop.model.ShoppingCart;
import com.internetshop.service.ShoppingCartService;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Inject
    private UserDao userDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().remove(product);
        shoppingCartDao.update(shoppingCart);
        return true;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.get(userId).orElseThrow();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCartDao.get(shoppingCart.getUserId()).orElseThrow().getProducts();
    }

    @Override
    public ShoppingCart create(Long userId) {
        return shoppingCartDao.create(new ShoppingCart(userId));
    }
}
