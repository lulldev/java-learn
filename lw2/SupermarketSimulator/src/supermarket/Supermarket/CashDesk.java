package supermarket.Supermarket;

import com.sun.tools.javac.util.ArrayUtils;
import supermarket.Customer.Customer;
import supermarket.Product.Product;
import supermarket.Product.ProductStock;
import supermarket.Supermarket.Report;

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

    public void serveNextCustomer(List<Customer> customers, ProductStock productStock, Report report) {
        if (customersQueeIds.size() > 0) {
            int targetCustomerId = customersQueeIds.get(customersQueeIds.size() - 1);
            List<Product> productList = productStock.GetProductList();
            for(Customer customer : customers) {
                if (customer.getId() == targetCustomerId) {
                    Basket clientBasket = customer.getBasket();

                    if (clientBasket.BasketSize() > 0) {
                        Logger.show("serve customer id: " + customer.getId() + ", type: " + customer.getCustomerType());
                        final BigDecimal[] totalPrice = {new BigDecimal(0)};
                        clientBasket.GetContent().forEach((productId, productCount) -> {
                            for (Product stockProduct : productList) {
                                if (stockProduct.GetProductId() == (productId + 1)) {
                                    if (!customer.IsAdult() && stockProduct.IsAdult()) {
                                        System.out.println("[!] Reject adult product for children - " + stockProduct.toString());
                                        productStock.returnProduct(stockProduct.GetProductId(), productCount);
                                        continue;
                                    }
                                    BigDecimal calculateTotal = stockProduct.GetProductPrice().multiply(new BigDecimal(productCount));
                                    totalPrice[0] = calculateTotal.plus();
                                    System.out.println("[+] calculate "  + stockProduct.toString() + " (" + productCount + ") "
                                            + stockProduct.GetProductMeasure() + ", price: " + stockProduct.GetProductPrice());
                                }
                            }
                        });

                        if (totalPrice[0].compareTo(new BigDecimal(0)) == 0) {
                            break;
                        }

                        System.out.println("-> Backet total price: " + totalPrice[0]);
                        System.out.println("-> Сustomer cash: " + customer.getCash());
                        if (customer.IsRetired()) {
                            System.out.println("-> Сustomer bonus: " + customer.getBonuses());
                        }

                        if (customer.getCash().compareTo(totalPrice[0]) >= 0) {
                            // todo: payment method
                            System.out.println("-> Customer use cash: " + customer.getCash());
                            customer.UpdateCash(customer.getCash().subtract(totalPrice[0]));
                            System.out.println("[+] payment complete");
                            System.out.println("-> Customer remain cash " + customer.getCash());
                            report.addSoldProducts(clientBasket.GetContent());
                        }
                        else if (customer.getCash().compareTo(totalPrice[0]) < 0
                                && (customer.IsRetired() && (totalPrice[0].compareTo(new BigDecimal(customer.getBonuses())) < 0))) {
                            System.out.println("-> Customer use bonuses: " + customer.getBonuses());
                            BigDecimal bdBonuses = new BigDecimal(customer.getBonuses()).subtract(totalPrice[0]);
                            customer.UpdateBonuses(bdBonuses.intValue());
                            System.out.println("[+] payment complete");
                            System.out.println("-> Customer remain bonuses " + customer.getBonuses());
                            report.addSoldProducts(clientBasket.GetContent());
                        }
                        else {
                            System.out.println("[!] Insufficient funds");
                            System.out.println("-> Products from basket return to stock");
                            clientBasket.GetContent().forEach(productStock::returnProduct);
                        }

                        customer.clearBasket();
                    }
                }
            }
            customersQueeIds.remove(customersQueeIds.size() - 1);
        }
    }
}
