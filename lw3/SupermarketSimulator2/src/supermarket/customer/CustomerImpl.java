package supermarket.customer;

import supermarket.backet.Backet;
import supermarket.backet.BacketImpl;
import java.math.BigDecimal;

public class CustomerImpl implements Customer {

    private int id;
    private CustomerType customerType;
    private BigDecimal cash;
    private int bonuses;
    private final Backet basket = new BacketImpl();

    public CustomerImpl(CustomerType customerType, BigDecimal cash, int bonuses) {
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

    public Backet getBasket() { return this.basket; }

    public final void putProductInBasket(int productId, int count) {
        this.basket.addProduct(productId, count);
    }

    public final void cameOutProductInBasket(int productId) {
        this.basket.deleteProduct(productId);
    }

    public final void clearBasket() {
        this.basket.clearBasket();
    }

    public final boolean issetProductsInBasket() {
        return this.basket.getBasketSize() > 0;
    }

    public final boolean isAdult() {
        return this.customerType == CustomerType.Adult || this.customerType == CustomerType.Retired;
    }

    public final boolean isRetired() {
        return this.customerType == CustomerType.Retired;
    }

    public final void updateCash(BigDecimal newCashVal) {
        this.cash = newCashVal;
    }

    public final void updateBonuses(int newBonusesVal) {
        this.bonuses = newBonusesVal;
    }

}
