package vlad.petrovskyi.internetshop.service.impl;

import java.util.List;
import vlad.petrovskyi.internetshop.dao.ItemDao;
import vlad.petrovskyi.internetshop.lib.Inject;
import vlad.petrovskyi.internetshop.lib.Service;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private ItemDao itemDao;

    @Override
    public Product create(Product product) {
        return itemDao.create(product);
    }

    @Override
    public Product get(Long id) {
        return itemDao.get(id).get();
    }

    @Override
    public List<Product> getAll() {
        return itemDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return itemDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return itemDao.delete(id);
    }
}
