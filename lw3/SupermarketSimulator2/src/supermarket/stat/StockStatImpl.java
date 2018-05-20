package supermarket.stat;

import java.util.HashMap;
import java.util.Map;

public class StockStatImpl implements StockStat {

    private final Map<Integer, Integer> soldProducts = new HashMap<>();

    public final void addSoldProducts(Map<Integer, Integer> recSoldProducts) {
        recSoldProducts.forEach((productId, productCount) -> {
            if (this.soldProducts.containsKey(productId)) {
                this.soldProducts.put(productId, this.soldProducts.get(productId) + productCount);
            } else {
                this.soldProducts.put(productId, productCount);
            }
        });
    }

    public final Map<Integer, Integer> getSoldProducts() {
        return this.soldProducts;
    }
}
