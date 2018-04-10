package supermarket.Supermarket;
//import supermarket.Product.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

//    List<Integer> products = new ArrayList<>();
    private final Map<Integer, Integer> products = new HashMap<>();


    public final void AddProduct(int productId, int count) {
        this.products.put(productId, count);
    }

    public final void DeleteProduct(int productId, int count) {
        //this.products.remove((Integer) productId);
        int storeCount = this.products.get(productId);
        if (storeCount >= count) {
            this.products.put(productId, this.products.get(productId) - count);
        }
    }

    public final int BasketSize() {
        return this.products.size();
    }

    public final int[] ToIntArray() {
        int[] ret = new int[this.products.size()];
        int i = 0;
        for (Integer key : this.products.keySet()) {
            ret[i++] = key;
        }
        return ret;
    }

    /*
    @Override
    public final String toString() {
        String productsInfo = "";
        return productsInfo;
    }*/
}
