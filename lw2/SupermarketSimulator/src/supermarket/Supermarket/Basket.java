package supermarket.Supermarket;
//import supermarket.Product.Product;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    List<Integer> products = new ArrayList<>();

    public final void AddProduct(int productId) {
        this.products.add(productId);
    }

    public final void DeleteProduct(int productId) {
        this.products.remove((Integer) productId);
    }

    public final int BasketSize() {
        return this.products.size();
    }

    public final int[] ToIntArray() {
        int[] ret = new int[this.products.size()];
        int i = 0;
        for (Integer e : this.products) ret[i++] = e.intValue();
        return ret;
    }

    /*
    @Override
    public final String toString() {
        String productsInfo = "";
        return productsInfo;
    }*/
}
