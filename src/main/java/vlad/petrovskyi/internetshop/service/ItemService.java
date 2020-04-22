package vlad.petrovskyi.internetshop.service;

import java.util.List;
import vlad.petrovskyi.internetshop.model.Item;

public interface ItemService {
    Item create(Item item);

    Item get(Long id);

    List<Item> getAll();

    Item update(Item item);

    boolean delete(Long id);
}
