package supermarket.Supermarket;

import org.jetbrains.annotations.Contract;
import supermarket.Product.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private List<Product> products;

    public final boolean AddProduct(Product product) {
        this.products.add(product);
        return true;
    }

    @Contract(pure = true)
    public final boolean DeleteProduct(int productId) {
        return true;
    }

    private final Map<String, BigDecimal> getProductsMap() {
        Map< String, BigDecimal > productsMap = new HashMap<String, BigDecimal>();
        for (Product product : this.products) {
            // TODO autoincrement
            productsMap.put(product.GetProductName(), product.GetProductPrice());
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
