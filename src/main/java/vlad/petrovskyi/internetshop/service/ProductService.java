package vlad.petrovskyi.internetshop.service;

import vlad.petrovskyi.internetshop.model.Product;

public interface ProductService extends GenericService<Product, Long> {
    Product create(Product product);

    Product update(Product product);
}
