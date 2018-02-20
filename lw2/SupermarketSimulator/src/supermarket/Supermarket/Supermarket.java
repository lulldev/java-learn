package supermarket.Supermarket;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Map;
import utils.Random;
import utils.Datetime;

import supermarket.Customer.*;
import supermarket.Product.*;

public class Supermarket {

    private static final int workingTimeMinutes = 1;

    private final SupermarketEvent marketEvent = new SupermarketEvent();;
    private boolean isOpen;
    private final List<Customer> customers = new ArrayList<>();
    private final ProductStock productStock = new ProductStock();
    private CashDesk cashDesk;

    // todo add logger

    public void openMarket() {
        this.isOpen = true;
    }

    public void closeMarket() {
        this.isOpen = false;
    }

    public int getWorkingTimeMinutes() {
        return workingTimeMinutes;
    }

    public void runMarketScenario() {

        if (!this.isOpen) {
            openMarket();
            logger("market is opened!");
            configureMarket();
        }

        // todo add products in market
        // todo - close market add to rnd event
        // todo - after close show report

        // TODO: init once and calls many this.marketEvent
        switch (marketEvent.getNextRandomEvent()) {
            case SupermarketEvent.EVENT_CUSTOMER_CAME_IN:
                addRandomCustomer();
                break;
            case SupermarketEvent.EVENT_CUSTOMER_CAME_OUT:
                removeRandomCustomer();
                break;
            case SupermarketEvent.EVENT_CUSTOMER_PUT_IN_BUSKET:
                break;
            case SupermarketEvent.EVENT_CUSTOMER_LAID_BUSKET:
                break;
            case SupermarketEvent.EVENT_CASHDESK_VALID_PAY:
                break;
            case SupermarketEvent.EVENT_CASHDESK_WRONG_PAY:
                break;
        }
    }

    private void finishWork() {
        // TODO: show report
    }

    private void configureMarket() {
        logger("Add products to Supermarket stock");
        productStock.GenerateRandomProductStore();
    }

    private void addRandomCustomer() {
        CustomerType customerType = CustomerType.getByCode((Random.getRandomInt(0, CustomerType.values().length)));
        int cash = Random.getRandomInt(0, 100);
        int bonuses = Random.getRandomInt(0, 10);
        Customer customer = new Customer(customerType, new BigDecimal(cash), new BigDecimal(bonuses));

        int newCustomerId = (customers.size() > 0) ?
                customers.get(customers.size() - 1).getId() + 1 : 1;
        customer.setId(newCustomerId);
        customers.add(customer);
        logger("new customer #" + customer.getId() + " arrived!");
    }

    private void removeRandomCustomer() {
        if (customers.size() > 0) {
            int rndCustomerIndex = Random.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            customers.remove(rndCustomerIndex);
            logger("customer #" + rndCustomer.getId() + " came out!");
        }
    }

//    private void addRandomProduct(productList) {
//        int prict = Random.getRandomInt(1, 100);
//        int disount = Random.getRandomInt(5, 15);
//        boolean isOnlyForAdult = (Random.getRandomInt(0, 10) >= 7);
//
//        Customer customer = new Customer(customerType, new BigDecimal(cash), new BigDecimal(bonuses));
//
//        int newCustomerId = (customers.size() > 0) ?
//                customers.get(customers.size() - 1).getId() + 1 : 1;
//        customer.setId(newCustomerId);
//        customers.add(customer);
//        logger("new customer #" + customer.getId() + " arrived!");
//    }

    private void logger(String action) {
        System.out.println("[" + Datetime.getCurrentDatetime() + "]" + " - " + action);
    }
}
