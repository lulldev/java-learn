package product_stock;

import org.junit.Assert;
import org.junit.Test;
import supermarket.payment.Discount;
import supermarket.product.ProductImpl;
import supermarket.product.ProductMeasure;
import supermarket.product_stock.ProductStock;
import supermarket.product_stock.ProductStockImpl;

import java.math.BigDecimal;
public class ProductStockImplTest extends Assert {

    private final ProductStock productStock = new ProductStockImpl();

    public ProductStockImplTest() {
        this.productStock.generateRandomProductStore();
    }

    @Test
    public void addProductToStock() {
        assertEquals(productStock.getProductList().size(), 5);

        this.productStock.addProductToList(
                new ProductImpl(
                        1,
                        "Banan",
                        new Discount(10),
                        new BigDecimal(25),
                        false,
                        ProductMeasure.KG)
        );

        this.productStock.addProductToList(
                new ProductImpl(
                        2,
                        "Bread",
                        new Discount(0),
                        new BigDecimal(25),
                        false,
                        ProductMeasure.KG)
        );

        assertEquals(productStock.getProductList().size(), 7);
    }

    @Test
    public void getProductById() {
        assertEquals(productStock.getProductById(1).getProductId(),2);
    }

}