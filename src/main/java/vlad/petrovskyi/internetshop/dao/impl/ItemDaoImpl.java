package vlad.petrovskyi.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;
import vlad.petrovskyi.internetshop.dao.ItemDao;
import vlad.petrovskyi.internetshop.db.Storage;
import vlad.petrovskyi.internetshop.lib.Dao;
import vlad.petrovskyi.internetshop.model.Product;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Product create(Product product) {
        Storage.addItem(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.PRODUCTS.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.PRODUCTS;
    }

    @Override
    public Product update(Product product) {
        IntStream.range(0, Storage.PRODUCTS.size() - 1)
                .filter(x -> product.getId().equals(Storage.PRODUCTS.get(x).getId()))
                .forEach(i -> Storage.PRODUCTS.set(i, product));
        return product;
    }

    @Override
    public boolean delete(Long id) {
        Product product = Storage.PRODUCTS.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No element found with id" + id));
        return Storage.PRODUCTS.remove(product);
    }
}
