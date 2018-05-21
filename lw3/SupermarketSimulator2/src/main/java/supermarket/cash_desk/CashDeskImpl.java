package supermarket.cash_desk;

import supermarket.backet.Backet;
import supermarket.backet_calculator.BacketCalculator;
import supermarket.backet_calculator.BacketCalculatorImpl;
import supermarket.customer.Customer;
import supermarket.payment.Bill;
import supermarket.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import supermarket.product_stock.ProductStock;
import supermarket.stat.StockStat;
import utils.Logger;


public class CashDeskImpl implements CashDesk {

    private List<Integer> customersQueeIds = new ArrayList<>();

    public void addCustomerToQuee(int customerId) {
        customersQueeIds.add(customerId);
    }

    public void removeCustomerFromQuee(int customerId) {
        List<Integer> result = new ArrayList<>();

        for (int currentCustomerId : this.customersQueeIds) {
            if (currentCustomerId != customerId) {
                result.add(currentCustomerId);
            }
        }
        this.customersQueeIds = result;
    }

    public void serveNextCustomer(List<Customer> customers, ProductStock productStock, StockStat stockStat) {
        if (customersQueeIds.size() > 0) {
            int targetCustomerId = customersQueeIds.get(0);
            for (Customer customer : customers) {
                if (customer.getId() == targetCustomerId) {
                    Backet clientBasket = customer.getBasket();
                    BacketCalculator backetCalculator = new BacketCalculatorImpl(productStock);
                    backetCalculator.setBacketContent(clientBasket.getContent());
                    backetCalculator.calculateBacket();
                    BigDecimal totalPrice = backetCalculator.getBacketTotal();

                    if (totalPrice.compareTo(new BigDecimal(0)) == 0) {
                        break;
                    }

                    Logger.message("-> backet total price: " + totalPrice, false);
                    Logger.message("-> Сustomer cash: " + customer.getCash(), false);
                    Logger.message("-> Сustomer card cash: " + customer.getCardCash(), false);
                    if (customer.isRetired()) {
                        Logger.message("-> Сustomer bonus: " + customer.getBonuses(), false);
                    }

                    if (customer.getCash().compareTo(totalPrice) >= 0) {
                        Logger.message("-> customer use cash: " + customer.getCash(), false);
                        customer.pay(new Bill(PaymentMethod.Cash, totalPrice));
                        Logger.message("[+] payment complete", false);
                        Logger.message("-> customer remain cash " + customer.getCash(), false);
                        Logger.message("-> customer remain card cash " + customer.getCardCash(), false);
                        stockStat.addSoldProducts(clientBasket.getContent());
                    }
                    else if (customer.getCardCash().compareTo(totalPrice) >= 0) {
                        Logger.message("-> customer use card cash: " + customer.getCardCash(), false);
                        customer.pay(new Bill(PaymentMethod.Card, totalPrice));
                        Logger.message("[+] payment complete", false);
                        Logger.message("-> customer remain cash " + customer.getCash(), false);
                        Logger.message("-> customer remain card cash " + customer.getCardCash(), false);
                        stockStat.addSoldProducts(clientBasket.getContent());
                    }
                    else if (customer.getCash().compareTo(totalPrice) < 0
                            && (customer.isRetired() && (totalPrice.compareTo(new BigDecimal(customer.getBonuses())) < 0))) {
                        Logger.message("-> customer use bonuses: " + customer.getBonuses(), false);
                        customer.pay(new Bill(PaymentMethod.Bonuses, totalPrice));
                        Logger.message("[+] payment complete", false);
                        Logger.message("-> customer remain bonuses " + customer.getBonuses(), false);
                        stockStat.addSoldProducts(clientBasket.getContent());
                    } else {
                        Logger.message("[!] Insufficient funds", false);
                        Logger.message("-> Products from basket return to stock", false);
                        clientBasket.getContent().forEach(productStock::returnProduct);
                    }
                    customer.clearBasket();
                }
            }
            customersQueeIds.remove(0);
        }
    }
}
