package vlad.petrovskyi.internetshop.service.impl;

import java.util.List;
import vlad.petrovskyi.internetshop.dao.ShoppingCartDao;
import vlad.petrovskyi.internetshop.dao.UserDao;
import vlad.petrovskyi.internetshop.lib.Inject;
import vlad.petrovskyi.internetshop.lib.Service;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.ShoppingCart;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;

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
        return shoppingCartDao.getAll().stream()
                .filter(x -> x.getUser().getId().equals(userId))
                .findFirst()
                .get();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCartDao.get(shoppingCart.getId()).get().getProducts();
    }

    @Override
    public ShoppingCart create(Long userId) {
        return shoppingCartDao.create(new ShoppingCart(userDao.get(userId).get()));
    }
}
