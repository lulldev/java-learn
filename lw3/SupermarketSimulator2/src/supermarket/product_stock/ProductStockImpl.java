package supermarket.product_stock;

import supermarket.product.Product;
import supermarket.product.ProductImpl;
import supermarket.product.ProductMeasure;
import supermarket.payment.Discount;
import utils.RandomUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductStockImpl implements ProductStock {

    private final Map<Integer, Integer> productStore = new HashMap<>();
    private final List<Product> productList = new ArrayList<>();

    public final List<Product> getProductList() {
        return productList;
    }

    public final Product getProductById(int productId) {
        return productList.get(productId);
    }

    public final boolean deductProduct(int productId, int count) {
        if (this.productStore.containsKey(productId)) {
            int storeCount = this.productStore.get(productId);
            if (storeCount > 0 && storeCount >= count) {
                this.productStore.put(productId, this.productStore.get(productId) - count);
                return true;
            }
        }
        return false;
    }

    public final void returnProduct(int productId, int count) {
        if (this.productStore.containsKey(productId)) {
            this.productStore.put(productId, this.productStore.get(productId) + count);
        }
    }

    public final void generateRandomProductStore() {

        fillProductList();

        int productsCount = productList.size();
        int randomCount;

        for (int i = 0; i < productsCount; i++) {
            Product newProduct = this.productList.get(i);
            randomCount = RandomUtil.getRandomInt(2, 20);
            productStore.put(newProduct.getProductId(), randomCount);

            System.out.println(" Add product # " + newProduct.getProductId());
            System.out.println(" Details: " + newProduct.getProductName() + ", price: " + newProduct.getProductPrice());
            System.out.println(" Total count: " + randomCount);
            System.out.println(" ---------------");
        }
    }

    // todo:
    private void fillProductList() {

        productList.add(new ProductImpl(
                1,
                "Bread",
                new Discount(0),
                new BigDecimal(10),
                false,
                ProductMeasure.PIECES)
        );

        productList.add(new ProductImpl(
                2,
                "Banan",
                new Discount(10),
                new BigDecimal(25),
                false,
                ProductMeasure.KG)
        );

        productList.add(new ProductImpl(
                3,
                "Beer",
                new Discount(5),
                new BigDecimal(30),
                true,
                ProductMeasure.PIECES)
        );

        productList.add(new ProductImpl(
                4,
                "Cigaro",
                new Discount(0),
                new BigDecimal(50),
                true,
                ProductMeasure.PIECES)
        );

        productList.add(new ProductImpl(
                5,
                "Chocolate",
                new Discount(15),
                new BigDecimal(40),
                false,
                ProductMeasure.PIECES)
        );
    }
}
