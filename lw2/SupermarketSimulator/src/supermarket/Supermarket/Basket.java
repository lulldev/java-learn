package supermarket.Supermarket;

import org.jetbrains.annotations.Contract;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private List<Product> products;

    public final boolean addProduct(Product product) {
        this.products.add(product);
        return true;
    }

    @Contract(pure = true)
    public final boolean deleteProduct(int productId) {
        return true;
    }

    private final Map<String, BigDecimal> getProductsMap() {
        Map< String, BigDecimal > productsMap = new HashMap<String, BigDecimal>();
        for (Product product : this.products) {
            // TODO autoincrement
            productsMap.put(product.getProductName(), product.getProductPrice());
        }
        return productsMap;
    }

    @Override
    public final String toString() {
        String productsInfo = "";
        // TODO each products map
        return productsInfo;
    }
}
