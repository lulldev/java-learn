package supermarket.Product;

import java.math.BigDecimal;
import java.util.*;
import utils.Random;

import supermarket.Supermarket.Discount;

public class ProductStock {

    // productId as count
    private final Map<Integer, Integer> productStore = new HashMap<>();
    private final List<Product> productList = new ArrayList<>();

    public final void GenerateRandomProductStore() {

        FillProductList();

        int productsCount = Random.getRandomInt(5, 10);
        int randomCount;
        int randomProductId;

        for(int i = 0; i < productsCount; i++) {
            randomProductId = Random.getRandomInt(1, productList.size());
            randomCount = Random.getRandomInt(2, 20);
            Product newProduct = this.productList.get(randomProductId);
            productStore.put(newProduct.GetProductId(), randomCount);

            System.out.println(" Add product # " + newProduct.GetProductId());
            System.out.println(" Details: " + newProduct.GetProductName() + ", price: " + newProduct.GetProductPrice());
            System.out.println(" Total count: " + randomCount);
            System.out.println(" ---------------");
        }
    }

    private void FillProductList() {
        // todo:
        Discount discount = new Discount();

        productList.add(new Product(1, "Bread", discount, new BigDecimal(10), false, ProductMeasure.PIECES));
        productList.add(new Product(2, "Banan", discount, new BigDecimal(25), false, ProductMeasure.KG));
        productList.add(new Product(3, "Beer", discount, new BigDecimal(30), true, ProductMeasure.PIECES));
        productList.add(new Product(4, "Cigaro", discount, new BigDecimal(50), true, ProductMeasure.PIECES));
        productList.add(new Product(5, "Chocolate", discount, new BigDecimal(40), false, ProductMeasure.PIECES));
    }

}
