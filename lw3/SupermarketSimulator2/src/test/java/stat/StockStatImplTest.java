package stat;

import org.junit.Assert;
import org.junit.Test;
import supermarket.stat.StockStat;
import supermarket.stat.StockStatImpl;

import java.util.HashMap;
import java.util.Map;

public class StockStatImplTest extends Assert {

    private StockStat stockStat = new StockStatImpl();

    @Test
    public void getSoldProducts() {
        Map<Integer, Integer> soldProducts = new HashMap<>();
        assertEquals(stockStat.getSoldProducts(), soldProducts);
        soldProducts.put(1, 10);
        soldProducts.put(2, 100);
        soldProducts.put(3, 100);
        stockStat.addSoldProducts(soldProducts);
        assertEquals(stockStat.getSoldProducts(), soldProducts);
    }

    @Test
    public void addSoldProducts() {
        Map<Integer, Integer> soldProducts = new HashMap<>();
        soldProducts.put(1, 10);
        soldProducts.put(2, 100);
        stockStat.addSoldProducts(soldProducts);
        assertEquals(stockStat.getSoldProducts(), soldProducts);
    }
}
