package supermarket.Customer;

import supermarket.Supermarket.Basket;
import supermarket.Product.Product;

import java.math.BigDecimal;

public class Customer {

    private int id;
    private CustomerType customerType;
    private BigDecimal cash;
    private BigDecimal bonuses;
    private final Basket basket = new Basket();

    /**
     * @param customerType
     * @param cash
     * @param bonuses
     */
    public Customer(CustomerType customerType, BigDecimal cash, BigDecimal bonuses) {
        this.id = -1; // nobody
        this.customerType = customerType;
        this.cash = cash;
        this.bonuses = bonuses;
    }

    public CustomerType getCustomerType() {
        return this.customerType;
    }

    public BigDecimal getCash() {
        return this.cash;
    }

    public BigDecimal getBonuses() {
        return this.bonuses;
    }

    public final void cameInProductInBasket(Product product) {
        this.basket.AddProduct(product);
    }

    public final void cameOutProductInBasket(int productId) {
        this.basket.DeleteProduct(productId);
    }

    public final String getBasketContent() {
        return this.basket.toString();
    }

    public final void setId(int customerId) {
        this.id = customerId;
    }

    public final int getId() {
        return this.id;
    }

}
