package vlad.petrovskyi.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import vlad.petrovskyi.internetshop.model.Item;

public class Storage {

    public static final List<Item> items = new ArrayList<>();
    private static Long itemId = 0L;

    public static void addItem(Item item) {
        itemId++;
        item.setId(itemId);
        Storage.items.add(item);
    }
}
