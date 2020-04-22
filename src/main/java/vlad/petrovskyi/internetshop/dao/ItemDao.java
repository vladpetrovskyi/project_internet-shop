package vlad.petrovskyi.internetshop.dao;

import java.util.List;
import java.util.Optional;
import vlad.petrovskyi.internetshop.model.Product;

public interface ItemDao {
    Product create(Product product);

    Optional<Product> get(Long id);

    List<Product> getAll();

    Product update(Product product);

    boolean delete(Long id);
}
