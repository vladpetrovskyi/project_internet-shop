package vlad.petrovskyi.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import vlad.petrovskyi.internetshop.dao.ProductDao;
import vlad.petrovskyi.internetshop.db.Storage;
import vlad.petrovskyi.internetshop.lib.Dao;
import vlad.petrovskyi.internetshop.model.Product;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.addItem(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.products.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        IntStream.range(0, Storage.products.size() - 1)
                .filter(x -> product.getId().equals(Storage.products.get(x).getId()))
                .forEach(i -> Storage.products.set(i, product));
        return product;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.products.removeIf(product -> product.getId().equals(id));
    }
}
