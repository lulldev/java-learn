package supermarket.Supermarket;

import java.util.List;

public class Basket {

    private List<Product> products;

    public final boolean addProduct(Product product) {
        this.products.add(product);
        return true;
    }

    public final boolean deleteProduct(int productId) {
        return true;
    }
}
