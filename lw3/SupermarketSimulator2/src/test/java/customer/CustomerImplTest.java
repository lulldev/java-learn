package customer;

import org.junit.Test;
import org.junit.Assert;
import supermarket.backet.BacketImpl;
import supermarket.customer.Customer;
import supermarket.customer.CustomerType;
import supermarket.customer.CustomerImpl;
import supermarket.payment.Bill;
import supermarket.payment.PaymentMethod;

import java.math.BigDecimal;


public class CustomerImplTest extends Assert {

    private final Customer customer = new CustomerImpl(
            CustomerType.Child,
            new BigDecimal(100),
            new BigDecimal(500),
            10);

    @Test
    public void idIsGenerated() {
        assertTrue(customer.getId() < 10000000 && customer.getId() >= 1);
    }

    @Test
    public void getCustomerType() {
        assertEquals(customer.getCustomerType(), CustomerType.Child);
    }

    @Test
    public void getCash() { assertEquals(customer.getCash(), new BigDecimal(100)); }

    @Test
    public void getCardCash() { assertEquals(customer.getCardCash(), new BigDecimal(500)); }

    @Test
    public void getBonuses() {
        assertEquals(customer.getBonuses(), 10);
    }

    @Test
    public void isAdult() {
        assertTrue(!customer.isAdult());
    }

    @Test
    public void isRetired() {
        assertTrue(!customer.isRetired());
    }

    @Test
    public void issetProductsInBasket() {
        assertTrue(!customer.issetProductsInBasket());
    }

    @Test
    public void getBasket() {
        assertEquals(customer.getBasket().getBasketSize(), new BacketImpl().getBasketSize());
    }

    @Test
    public void putProductInBasket() {
        assertTrue(!customer.issetProductsInBasket());
        customer.putProductInBasket(1, 1);
        assertTrue(customer.issetProductsInBasket());
    }

    @Test
    public void cameOutProductInBasket() {
        customer.clearBasket();
        assertTrue(!customer.issetProductsInBasket());
        customer.putProductInBasket(1, 1);
        assertTrue(customer.issetProductsInBasket());
        customer.cameOutProductInBasket(1);
        assertTrue(!customer.issetProductsInBasket());
    }

    @Test
    public void clearBasket() {
        customer.clearBasket();
        assertTrue(!customer.issetProductsInBasket());
        customer.putProductInBasket(1, 1);
        customer.putProductInBasket(2, 5);
        assertTrue(customer.issetProductsInBasket());
        customer.clearBasket();
        assertTrue(!customer.issetProductsInBasket());
    }

    @Test
    public void pay() {
        assertEquals(customer.getCash(), new BigDecimal(100));
        customer.pay(new Bill(PaymentMethod.Cash, new BigDecimal(10)));
        assertEquals(customer.getCash(), new BigDecimal(90));
        customer.pay(new Bill(PaymentMethod.Card, new BigDecimal(100)));
        assertEquals(customer.getCardCash(), new BigDecimal(400));
        customer.pay(new Bill(PaymentMethod.Bonuses, new BigDecimal(10)));
        assertEquals(customer.getBonuses(), new BigDecimal(0).intValue());
    }
}
