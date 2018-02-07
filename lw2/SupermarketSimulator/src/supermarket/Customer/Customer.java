package supermarket.Customer;

import supermarket.Supermarket.Basket;
import supermarket.Supermarket.Product;
import supermarket.Customer.CustomerType;

import java.math.BigDecimal;

public class Customer {

    private final Basket basket = new Basket();
    private CustomerType customerType;
    private BigDecimal cash;
    private BigDecimal bonuses;

    /**
     * @param customerType
     * @param cash
     * @param bonuses
     */
    public Customer(CustomerType customerType, BigDecimal cash, BigDecimal bonuses) {
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
        this.basket.addProduct(product);
    }

    public final void cameOutProductInBasket(int productId) {
        this.basket.deleteProduct(productId);
    }

    public final String getBasketContent() {
        return this.basket.toString();
    }

}
