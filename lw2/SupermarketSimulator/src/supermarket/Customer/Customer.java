package supermarket.Customer;

import supermarket.Supermarket.Basket;
import supermarket.Product.Product;

import java.math.BigDecimal;
import java.util.List;

public class Customer {

    private int id;
    private CustomerType customerType;
    private BigDecimal cash;
    private int bonuses;
    private final Basket basket = new Basket();

    /**
     * @param customerType
     * @param cash
     * @param bonuses
     */
    public Customer(CustomerType customerType, BigDecimal cash, int bonuses) {
        this.id = (int) System.currentTimeMillis();
        this.customerType = customerType;
        this.cash = cash;
        this.bonuses = bonuses;
    }

    public final int getId() {
        return this.id;
    }

    public CustomerType getCustomerType() {
        return this.customerType;
    }

    public BigDecimal getCash() {
        return this.cash;
    }

    public int getBonuses() {
        return this.bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses += bonuses;
    }

    public Basket getBasket() { return this.basket; }

    public final void putProductInBasket(int productId, int count) {
        this.basket.AddProduct(productId, count);
    }

    public final void cameOutProductInBasket(int productId) {
        this.basket.DeleteProduct(productId);
    }

    public final void clearBasket() {
        this.basket.ClearBasket();
    }

    public final boolean issetProductsInBasket() {
        return this.basket.BasketSize() > 0;
    }

    public final boolean IsAdult() {
        return this.customerType == CustomerType.Adult || this.customerType == CustomerType.Retired;
    }

    public final boolean IsRetired() {
        return this.customerType == CustomerType.Retired;
    }

    public final void UpdateCash(BigDecimal newCashVal) {
        this.cash = newCashVal;
    }

    public final void UpdateBonuses(int newBonusesVal) {
        this.bonuses = newBonusesVal;
    }

}
