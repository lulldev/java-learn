package product;

import org.junit.Assert;
import org.junit.Test;
import supermarket.backet.Backet;
import supermarket.backet.BacketImpl;
import supermarket.payment.Discount;
import supermarket.product.Product;
import supermarket.product.ProductImpl;
import supermarket.product.ProductMeasure;

import java.math.BigDecimal;

public class ProductImplTest extends Assert {

    private final Product product = new ProductImpl(
            1,
            "bread",
            new Discount(10),
            new BigDecimal(100),
            false,
            ProductMeasure.PIECES
    );

    @Test
    public void getProductId() {
        assertEquals(product.getProductId(), 1);
    }

    @Test
    public void getProductName() {
        assertEquals(product.getProductName(), "bread");
    }

    @Test
    public void getProductPrice() {
        assertEquals(product.getProductPrice(), new BigDecimal(100));
    }

    @Test
    public void getProductMeasure() {
        assertEquals(product.getProductMeasure(), ProductMeasure.PIECES);
    }

    @Test
    public void isAdult() {
        assertEquals(product.isAdult(), false);
    }

    @Test
    public void toStringWork() {
        assertEquals(product.toString(), product.getProductName() + " " + product.getProductPrice().toString());
    }
}