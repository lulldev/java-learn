package supermarket.product_stock;

import supermarket.product.Product;
import java.util.List;

public interface ProductStock {
    void addProductToList(Product product);
    List<Product> getProductList();
    Product getProductById(int productId);
    boolean deductProduct(int productId, int count);
    void returnProduct(int productId, int count);
    void generateRandomProductStore();
}
