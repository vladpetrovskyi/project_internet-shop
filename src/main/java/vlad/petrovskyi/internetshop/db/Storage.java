package vlad.petrovskyi.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import vlad.petrovskyi.internetshop.model.Product;

public class Storage {

    public static final List<Product> PRODUCTS = new ArrayList<>();
    private static Long itemId = 0L;

    public static void addItem(Product product) {
        itemId++;
        product.setId(itemId);
        Storage.PRODUCTS.add(product);
    }
}
