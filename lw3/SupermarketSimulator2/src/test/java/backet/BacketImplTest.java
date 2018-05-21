package backet;

import org.junit.Assert;
import org.junit.Test;
import supermarket.backet.Backet;
import supermarket.backet.BacketImpl;
import java.util.HashMap;
import java.util.Map;

public class BacketImplTest extends Assert {

    private final Backet backet = new BacketImpl();

    @Test
    public void addProduct() {
        assertEquals(backet.getBasketSize(), 0);
        backet.addProduct(1, 2);
        assertEquals(backet.getBasketSize(), 1);
        backet.addProduct(2, 2);
        assertEquals(backet.getBasketSize(), 2);
    }

    @Test
    public void deleteProduct() {
        backet.clearBasket();
        assertEquals(backet.getBasketSize(), 0);
        backet.addProduct(1, 2);
        assertEquals(backet.getBasketSize(), 1);
        backet.deleteProduct(1);
        assertEquals(backet.getBasketSize(), 0);
    }

    @Test
    public void clearBasket() {
        backet.clearBasket();
        assertEquals(backet.getBasketSize(), 0);
        backet.addProduct(1, 2);
        backet.addProduct(2, 2);
        backet.addProduct(3, 2);
        backet.clearBasket();
        assertEquals(backet.getBasketSize(), 0);
    }

    @Test
    public void getBasketSize() {
        backet.clearBasket();
        assertEquals(backet.getBasketSize(), 0);
        backet.addProduct(1, 2);
        backet.addProduct(2, 2);
        backet.addProduct(3, 2);
        assertEquals(backet.getBasketSize(), 3);
    }

    @Test
    public void getContent() {
        Map<Integer, Integer> products = new HashMap<>();
        assertEquals(backet.getContent(), products);
        backet.addProduct(1, 2);
        backet.addProduct(2, 2);
        Backet backet2 = new BacketImpl();
        backet2.addProduct(1, 2 );
        backet2.addProduct(2, 2 );
        assertEquals(backet.getContent(), backet2.getContent());
    }
}
