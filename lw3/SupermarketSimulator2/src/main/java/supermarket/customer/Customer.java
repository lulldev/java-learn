package supermarket.customer;

//import ru.mifsan.javacore2017.bucket.Bucket;
//import ru.mifsan.javacore2017.payment.Bill;
//import ru.mifsan.javacore2017.payment.PaymentMethod;
import supermarket.backet.Backet;
import supermarket.payment.Bill;

import java.math.BigDecimal;

public interface Customer {
    int getId();
    CustomerType getCustomerType();
    BigDecimal getCardCash();
    BigDecimal getCash();
    int getBonuses();
    Backet getBasket();
    void putProductInBasket(int productId, int count);
    void cameOutProductInBasket(int productId);
    void clearBasket();
    boolean issetProductsInBasket();
    boolean isAdult();
    boolean isRetired();
    void pay(Bill bill);
}