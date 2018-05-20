package supermarket.backet_calculator;

import supermarket.product_stock.ProductStock;

import java.math.BigDecimal;
import java.util.Map;

public interface BacketCalculator {
    void setBacketContent(Map<Integer, Integer> backetContent);
    BigDecimal getBacketTotal();
    void calculateBacket();
}
