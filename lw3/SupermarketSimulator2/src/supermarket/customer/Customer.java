package supermarket.customer;

//import ru.mifsan.javacore2017.bucket.Bucket;
//import ru.mifsan.javacore2017.payment.Bill;
//import ru.mifsan.javacore2017.payment.PaymentMethod;
import supermarket.backet.Backet;

import java.math.BigDecimal;

public interface Customer {
    int getId();
    CustomerType getCustomerType();
    BigDecimal getCash();
    int getBonuses();
    Backet getBasket();
    void putProductInBasket(int productId, int count);
    void cameOutProductInBasket(int productId);
    void clearBasket();
    boolean issetProductsInBasket();
    boolean isAdult();
    boolean isRetired();
    void updateCash(BigDecimal newCashVal); // todo
    void updateBonuses(int newBonusesVal); // todo
//    boolean pay(Bill bill);
//    PaymentMethod getDesiredPaymentMethod(double totalPaymentAmount);
}