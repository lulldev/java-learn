package supermarket.stat;

import java.util.Map;

public interface StockStat {
    void addSoldProducts(Map<Integer, Integer> recSoldProducts);
    Map<Integer, Integer> getSoldProducts();
}
