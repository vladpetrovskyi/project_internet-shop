package vlad.petrovskyi.internetshop;

import java.math.BigDecimal;
import java.util.List;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.service.ProductService;

public class Application {
    private static final Injector injector = Injector.getInstance("vlad.petrovskyi.internetShop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        dbInitializer(productService);

        List<Product> productList = productService.getAll();
        for (Product product : productList) {
            System.out.println(product.toString());
        }

        Product productTest = productService.get(2L);
        System.out.println(productTest);

        productTest.setPrice(new BigDecimal("2000.00"));
        productTest.setName("iPhone 5S");
        productService.update(productTest);
        System.out.println(productTest);

        productService.delete(2L);
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    private static void dbInitializer(ProductService productService) {
        productService.create(new Product("iPhone 11", new BigDecimal("999.00")));
        productService.create(new Product("iPhone 11 Pro", new BigDecimal("1099.00")));
        productService.create(new Product("iPhone 11 Pro Max", new BigDecimal("1299.00")));
    }
}
