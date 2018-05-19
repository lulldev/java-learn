package supermarket.Product;

import java.math.BigDecimal;
import java.util.*;
import utils.RandomUtil;

import supermarket.Supermarket.Discount;

public class ProductStock {

    private final Map<Integer, Integer> productStore = new HashMap<>();
    private final List<Product> productList = new ArrayList<>();

    public final void GenerateRandomProductStore() {

        FillProductList();

        int productsCount = productList.size();
        int randomCount;

        for (int i = 0; i < productsCount; i++) {
            Product newProduct = this.productList.get(i);
            randomCount = RandomUtil.getRandomInt(2, 20);
            productStore.put(newProduct.GetProductId(), randomCount);

            System.out.println(" Add product # " + newProduct.GetProductId());
            System.out.println(" Details: " + newProduct.GetProductName() + ", price: " + newProduct.GetProductPrice());
            System.out.println(" Total count: " + randomCount);
            System.out.println(" ---------------");
        }
    }

    public final List<Product> GetProductList() {
        return productList;
    }

    public final void returnProduct(int productId, int count) {
        if (this.productStore.containsKey(productId)) {
            this.productStore.put(productId, this.productStore.get(productId) + count);
        }
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

    public final Product GetProductById(int productId) {
        return productList.get(productId);
    }

    private void FillProductList() {

        productList.add(new Product(
                1,
                "Bread",
                new Discount(0),
                new BigDecimal(10),
                false,
                ProductMeasure.PIECES)
        );

        productList.add(new Product(
                2,
                "Banan",
                new Discount(10),
                new BigDecimal(25),
                false,
                ProductMeasure.KG)
        );

        productList.add(new Product(
                3,
                "Beer",
                new Discount(5),
                new BigDecimal(30),
                true,
                ProductMeasure.PIECES)
        );

        productList.add(new Product(
                4,
                "Cigaro",
                new Discount(0),
                new BigDecimal(50),
                true,
                ProductMeasure.PIECES)
        );

        productList.add(new Product(
                5,
                "Chocolate",
                new Discount(15),
                new BigDecimal(40),
                false,
                ProductMeasure.PIECES)
        );
    }

}
