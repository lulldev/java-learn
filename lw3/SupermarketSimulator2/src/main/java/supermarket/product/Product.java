package supermarket.product;

import java.math.BigDecimal;

public interface Product {
    int getProductId();
    String getProductName();
    BigDecimal getProductPrice();
    ProductMeasure getProductMeasure();
    boolean isAdult();
}
