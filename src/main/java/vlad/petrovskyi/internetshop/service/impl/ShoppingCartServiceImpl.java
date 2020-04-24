package vlad.petrovskyi.internetshop.service.impl;

import java.util.List;
import java.util.stream.IntStream;

import vlad.petrovskyi.internetshop.dao.ShoppingCartDao;
import vlad.petrovskyi.internetshop.lib.Inject;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.ShoppingCart;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        if (shoppingCartDao.getAll().stream()
                .anyMatch(c -> c.getId().equals(shoppingCart.getId())
                        || c.getUser().equals(shoppingCart.getUser()))) {
            return shoppingCartDao.update(shoppingCart);
        }
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCartDao.get(shoppingCart.getId()).get().getProducts()
                .remove(IntStream.range(0, shoppingCartDao.get(shoppingCart.getId()).get()
                        .getProducts().size())
                        .filter(x -> shoppingCartDao.get(shoppingCart.getId()).get()
                                .getProducts().get(x).getId().equals(product.getId()))
                        .findFirst()
                        .getAsInt());
        return true;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCartDao.get(shoppingCart.getId()).get().getProducts().clear();
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
}
