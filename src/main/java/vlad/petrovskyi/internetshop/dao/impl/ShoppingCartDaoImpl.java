package vlad.petrovskyi.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import vlad.petrovskyi.internetshop.dao.ShoppingCartDao;
import vlad.petrovskyi.internetshop.db.Storage;
import vlad.petrovskyi.internetshop.lib.Dao;
import vlad.petrovskyi.internetshop.model.ShoppingCart;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addShoppingCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Storage.shoppingCarts.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(x -> shoppingCart.getId().equals(Storage.shoppingCarts.get(x).getId()))
                .forEach(s -> Storage.shoppingCarts.set(s, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.shoppingCarts.removeIf(shoppingCart -> shoppingCart.getId().equals(id));
    }
}
