package supermarket.Customer;

import supermarket.Supermarket.Basket;
import supermarket.Product.Product;

import java.math.BigDecimal;
import java.util.List;

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

    public final void putProductInBasket(int productId, int count) {
        this.basket.AddProduct(productId, count);
    }

    public final void cameOutProductInBasket(int productId, int count) {
        this.basket.DeleteProduct(productId, count);
    }

    public final Basket getBasket() {
        return this.basket;
    }

    // todo: maybe
//    public final String getBasketContent() {
//    }

    public final void setId(int customerId) {
        this.id = customerId;
    }

    public final int getId() {
        return this.id;
    }

}
