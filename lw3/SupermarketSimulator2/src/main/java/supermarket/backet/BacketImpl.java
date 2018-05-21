package supermarket.backet;

import java.util.HashMap;
import java.util.Map;

public class BacketImpl implements Backet {

    private final Map<Integer, Integer> products = new HashMap<>();

    public final void addProduct(int productId, int count) {
        this.products.put(productId, count);
    }

    public final void deleteProduct(int productId) {
        this.products.remove(productId);
    }

    public final void clearBasket() {
        this.products.clear();
    }

    public final int getBasketSize() {
        return this.products.size();
    }

    public final Map<Integer, Integer> getContent() {
        return products;
    }

}
