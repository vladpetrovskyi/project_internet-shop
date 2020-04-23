package vlad.petrovskyi.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
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
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartDao.getAll().stream()
                .filter(c -> c.getId().equals(shoppingCart.getId())
                        || c.getUser().equals(shoppingCart.getUser()))
                .findFirst();
        if (optionalShoppingCart.isPresent()) {
            Optional<Product> productOptional = shoppingCartDao.get(shoppingCart.getId()).get()
                    .getProducts().stream()
                    .filter(p -> p.getId().equals(product.getId()))
                    .findFirst();
            if (productOptional.isEmpty()) {
                shoppingCartDao.get(shoppingCart.getId()).get().getProducts().add(product);
                return shoppingCart;
            }
            return shoppingCart;
        }
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        OptionalInt index = IntStream.range(0, shoppingCartDao.get(shoppingCart.getId()).get()
                .getProducts().size())
                .filter(x -> shoppingCartDao.get(shoppingCart.getId()).get()
                        .getProducts().get(x).getId().equals(product.getId()))
                .findFirst();
        if (index.isPresent()) {
            shoppingCartDao.get(shoppingCart.getId()).get()
                    .getProducts().remove(index.getAsInt());
            return true;
        }
        return false;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCartDao.get(shoppingCart.getId()).get().getProducts().clear();
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
