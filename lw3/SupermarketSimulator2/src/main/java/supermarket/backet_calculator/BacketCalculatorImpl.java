package supermarket.backet_calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import supermarket.product.Product;
import supermarket.product_stock.ProductStock;
import utils.Logger;


public class BacketCalculatorImpl implements BacketCalculator {

    private Map<Integer, Integer> backetContent = new HashMap<>();
    private ProductStock currentStock;
    private BigDecimal[] total = {new BigDecimal(0)};

    public BacketCalculatorImpl(ProductStock productStock) {
        this.currentStock = productStock;
    }

    public void setBacketContent(Map<Integer, Integer> backetContent) {
        this.backetContent = backetContent;
    }

    public BigDecimal getBacketTotal() {
        List<Product> productList = this.currentStock.getProductList();
        this.backetContent.forEach((productId, productCount) -> {
            for (Product stockProduct : productList) {
                if (stockProduct.getProductId() == (productId + 1)) {
                    BigDecimal calculateTotal = stockProduct.getProductPrice().multiply(new BigDecimal(productCount));
                    this.total[0] = calculateTotal.plus();
                }
            }
        });
        return this.total[0];
    }

    public void calculateBacket() {
        List<Product> productList = this.currentStock.getProductList();
        this.backetContent.forEach((productId, productCount) -> {
            for (Product stockProduct : productList) {
                if (stockProduct.getProductId() == (productId + 1)) {
                    Logger.message("[+] calculate " + stockProduct.toString()
                            + " (" + productCount + ") "
                            + stockProduct.getProductMeasure() + ", price: "
                            + stockProduct.getProductPrice(), false);
                }
            }
        });
    }
}
