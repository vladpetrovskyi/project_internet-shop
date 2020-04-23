package vlad.petrovskyi.internetshop.service.impl;

import java.util.List;
import vlad.petrovskyi.internetshop.dao.ProductDao;
import vlad.petrovskyi.internetshop.lib.Inject;
import vlad.petrovskyi.internetshop.lib.Service;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).get();
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }
}
