package backet_calculator;

import org.junit.Assert;
import org.junit.Test;
import supermarket.backet_calculator.BacketCalculator;
import supermarket.backet_calculator.BacketCalculatorImpl;
import supermarket.product_stock.ProductStock;
import supermarket.product_stock.ProductStockImpl;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BacketCalculatorImplTest extends Assert {

    @Test
    public void getBacketTotal() {
        Map<Integer, Integer> backetContent = new HashMap<>();
        backetContent.put(1, 2);
        backetContent.put(2, 1);

        ProductStock productStock = new ProductStockImpl();
        productStock.generateRandomProductStore();
        BacketCalculator backetCalculator = new BacketCalculatorImpl(productStock);
        backetCalculator.setBacketContent(backetContent);

        System.out.println(backetCalculator.getBacketTotal());
        assertEquals(backetCalculator.getBacketTotal(), new BigDecimal(30));
    }
}
