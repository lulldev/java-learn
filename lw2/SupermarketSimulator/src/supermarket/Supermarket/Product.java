package supermarket.Supermarket;

import java.math.BigDecimal;

public class Product {

    private int productId;
    private String productName;
    private Discount discount;
    private BigDecimal price;
    private boolean isOnlyForAdult;

    public Product(int productId, String productName, Discount discount, BigDecimal price, boolean isOnlyForAdult) {
        this.productId = productId;
        this.productName = productName;
        this.discount = discount;
        this.price = price;
        this.isOnlyForAdult = isOnlyForAdult;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final BigDecimal getProductPrice() {
        return this.price;
    }

    @Override
    public final String toString() {
        return this.productName + " " + this.price;
    }

}
