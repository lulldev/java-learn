package supermarket.product;

import supermarket.payment.Discount;
import java.math.BigDecimal;

public class ProductImpl implements Product {

    private int productId;
    private String productName;
    private Discount discount;
    private BigDecimal price;
    private boolean isOnlyForAdult;
    private ProductMeasure productMeasure;

    public ProductImpl(int productId, String productName, Discount discount,
                BigDecimal price, boolean isOnlyForAdult, ProductMeasure measure) {
        this.productId = productId;
        this.productName = productName;
        this.discount = discount;
        this.price = price;
        this.isOnlyForAdult = isOnlyForAdult;
        this.productMeasure = measure;
    }

    public final int getProductId() {
        return this.productId;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final BigDecimal getProductPrice() {
        return this.price;
    }

    public final ProductMeasure getProductMeasure() { return this.productMeasure; }

    public final boolean isAdult() { return this.isOnlyForAdult; }

    @Override
    public final String toString() {
        return this.productName + " " + this.price;
    }
}
