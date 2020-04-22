package vlad.petrovskyi.internetshop;

import java.math.BigDecimal;
import java.util.List;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Item;
import vlad.petrovskyi.internetshop.service.ItemService;

public class Application {
    private static final Injector injector = Injector.getInstance("vlad.petrovskyi.internetShop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);

        dbInitializer(itemService);

        List<Item> itemList = itemService.getAll();
        for (Item item: itemList) {
            System.out.println(item.toString());
        }

        Item itemTest = itemService.get(2L);
        System.out.println(itemTest);

        itemTest.setPrice(new BigDecimal("2000.00"));
        itemTest.setName("iPhone 5S");
        itemService.update(itemTest);
        System.out.println(itemTest);

        itemService.delete(2L);
        for (Item item: itemList) {
            System.out.println(item.toString());
        }
    }

    private static void dbInitializer(ItemService itemService) {
        itemService.create(new Item("iPhone 11", new BigDecimal("999.00")));
        itemService.create(new Item("iPhone 11 Pro", new BigDecimal("1099.00")));
        itemService.create(new Item("iPhone 11 Pro Max", new BigDecimal("1299.00")));
    }
}
