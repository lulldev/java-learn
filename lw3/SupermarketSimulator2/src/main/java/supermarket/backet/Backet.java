package supermarket.backet;

import java.util.Map;

public interface Backet {
    void addProduct(int productId, int count);
    void deleteProduct(int productId);
    void clearBasket();
    int getBasketSize();
    Map<Integer, Integer> getContent();
}
