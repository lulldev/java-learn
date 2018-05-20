package supermarket.cash_desk;

import supermarket.backet.Backet;
import supermarket.customer.Customer;
import supermarket.product.Product;
import supermarket.product.ProductStock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import supermarket.report.Report;
import utils.Logger;


public class CashDesk {

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
                    Backet clientBasket = customer.getBasket();

                    if (clientBasket.getBasketSize() > 0) {
                        Logger.show("serve customer id: " + customer.getId() + ", type: " + customer.getCustomerType());
                        final BigDecimal[] totalPrice = {new BigDecimal(0)};
                        clientBasket.getContent().forEach((productId, productCount) -> {
                            for (Product stockProduct : productList) {
                                if (stockProduct.getProductId() == (productId + 1)) {
                                    if (!customer.isAdult() && stockProduct.isAdult()) {
                                        System.out.println("[!] Reject adult product for children - " + stockProduct.toString());
                                        productStock.returnProduct(stockProduct.getProductId(), productCount);
                                        continue;
                                    }
                                    BigDecimal calculateTotal = stockProduct.getProductPrice().multiply(new BigDecimal(productCount));
                                    totalPrice[0] = calculateTotal.plus();
                                    System.out.println("[+] calculate "  + stockProduct.toString() + " (" + productCount + ") "
                                            + stockProduct.getProductMeasure() + ", price: " + stockProduct.getProductPrice());
                                }
                            }
                        });

                        if (totalPrice[0].compareTo(new BigDecimal(0)) == 0) {
                            break;
                        }

                        System.out.println("-> backet total price: " + totalPrice[0]);
                        System.out.println("-> Сustomer cash: " + customer.getCash());
                        if (customer.isRetired()) {
                            System.out.println("-> Сustomer bonus: " + customer.getBonuses());
                        }

                        if (customer.getCash().compareTo(totalPrice[0]) >= 0) {
                            // todo: payment method
                            System.out.println("-> customer use cash: " + customer.getCash());
                            customer.updateCash(customer.getCash().subtract(totalPrice[0]));
                            System.out.println("[+] payment complete");
                            System.out.println("-> customer remain cash " + customer.getCash());
                            report.addSoldProducts(clientBasket.getContent());
                        }
                        else if (customer.getCash().compareTo(totalPrice[0]) < 0
                                && (customer.isRetired() && (totalPrice[0].compareTo(new BigDecimal(customer.getBonuses())) < 0))) {
                            System.out.println("-> customer use bonuses: " + customer.getBonuses());
                            BigDecimal bdBonuses = new BigDecimal(customer.getBonuses()).subtract(totalPrice[0]);
                            customer.updateBonuses(bdBonuses.intValue());
                            System.out.println("[+] payment complete");
                            System.out.println("-> customer remain bonuses " + customer.getBonuses());
                            report.addSoldProducts(clientBasket.getContent());
                        }
                        else {
                            System.out.println("[!] Insufficient funds");
                            System.out.println("-> Products from basket return to stock");
                            clientBasket.getContent().forEach(productStock::returnProduct);
                        }

                        customer.clearBasket();
                    }
                }
            }
            customersQueeIds.remove(customersQueeIds.size() - 1);
        }
    }
}
