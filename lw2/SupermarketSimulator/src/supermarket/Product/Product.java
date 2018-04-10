package supermarket.Product;

import supermarket.Supermarket.Discount;

import java.math.BigDecimal;

public class Product {

    private int productId;
    private String productName;
    private Discount discount;
    private BigDecimal price;
    private boolean isOnlyForAdult;
    private ProductMeasure productMeasure;

    public Product(int productId, String productName, Discount discount, BigDecimal price, boolean isOnlyForAdult, ProductMeasure measure) {
        this.productId = productId;
        this.productName = productName;
        this.discount = discount;
        this.price = price;
        this.isOnlyForAdult = isOnlyForAdult;
        this.productMeasure = measure;
    }

    public final int GetProductId() {
        return this.productId;
    }

    public final String GetProductName() {
        return this.productName;
    }

    public final BigDecimal GetProductPrice() {
        return this.price;
    }

    public final ProductMeasure GetProductMeasure() {
        return this.productMeasure;
    }

    @Override
    public final String toString() {
        return this.productName + " " + this.price;
    }

}
