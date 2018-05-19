package supermarket.Supermarket;

import com.sun.tools.javac.util.ArrayUtils;
import supermarket.Customer.Customer;
import supermarket.Product.Product;
import supermarket.Product.ProductStock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.Logger;


class CashDesk {

    private List<Integer> customersQueeIds = new ArrayList<>();
//    private PaymentMethod paymentMethod;
//    private Bill bill;

    public void addCustomerToQuee(int customerId) {
        customersQueeIds.add(customerId);
    }

    public void removeCustomerFromQuee(int customerId) {

        List<Integer> result = new ArrayList<>();

        for(int currentCustomerId : this.customersQueeIds) {
            if(currentCustomerId != customerId) {
                result.add(currentCustomerId);
            }
        }

        this.customersQueeIds = result;
    }

    public void serveNextCustomer(List<Customer> customers, ProductStock productStock) {
        if (customersQueeIds.size() > 0) {
            int targetCustomerId = customersQueeIds.get(customersQueeIds.size() - 1);
            List<Product> productList = productStock.GetProductList();
            for(Customer customer : customers) {
                if (customer.getId() == targetCustomerId) {
                    Basket clientBasket = customer.getBasket();

                    if (clientBasket.BasketSize() > 0) {
                        Logger.show("serve customer id: " + customer.getId() +
                                        ", type: " + customer.getCustomerType());
                        clientBasket.GetContent().forEach((productId, productCount) -> {
                            for (Product stockProduct : productList) {
                                if (stockProduct.GetProductId() == (productId + 1)) {
                                    if (!customer.IsAdult() && stockProduct.IsAdult()) {
                                        System.out.println("[!] Reject adult product for children - " + stockProduct.toString());
                                        productStock.returnProduct(stockProduct.GetProductId(), productCount);
                                        continue;
                                    }
                                    System.out.println("customer cash = " + customer.getCash());
                                    System.out.println("product total = " +
                                            (stockProduct.GetProductPrice().multiply(new BigDecimal(productCount))));
                                    System.out.println("-> buy "  + stockProduct.toString() + " (" + productCount +
                                            ") " + stockProduct.GetProductMeasure() + ", price: " + stockProduct.GetProductPrice()
                                    );
                                }
                            }
                        });
                        customer.clearBasket();
                    }
                }
            }
            customersQueeIds.remove(customersQueeIds.size() - 1);
        }
    }
}
