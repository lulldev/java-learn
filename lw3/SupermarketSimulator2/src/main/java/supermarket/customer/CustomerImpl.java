package supermarket.customer;

import supermarket.backet.Backet;
import supermarket.backet.BacketImpl;
import supermarket.payment.Bill;
import supermarket.payment.PaymentMethod;
import utils.RandomUtil;

import java.math.BigDecimal;

public class CustomerImpl implements Customer {

    private int id;
    private CustomerType customerType;
    private BigDecimal cash;
    private BigDecimal cardCash;
    private int bonuses;
    private final Backet basket = new BacketImpl();

    public CustomerImpl(CustomerType customerType, BigDecimal cash, BigDecimal cardCash, int bonuses) {
        this.id = RandomUtil.getRandomInt(1, 10000000);
        this.customerType = customerType;
        this.cash = cash;
        this.cardCash = cardCash;
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

    public BigDecimal getCardCash() { return this.cardCash; }

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

    public final void pay(Bill bill) {
        if (bill.getPaymentMethod() == PaymentMethod.Cash) {
            this.cash = this.cash.subtract(bill.getBillTotal());
        }
        else if (bill.getPaymentMethod() == PaymentMethod.Card) {
            this.cardCash = this.cardCash.subtract(bill.getBillTotal());
        }
        else if (bill.getPaymentMethod() == PaymentMethod.Bonuses) {
            this.bonuses -= bill.getBillTotal().intValue();
        }
    }
}
