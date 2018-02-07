package supermarket.Supermarket;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.math.BigDecimal;
import utils.Random;
import utils.Datetime;

import supermarket.Supermarket.CashDesk;
import supermarket.Supermarket.SupermarketEvent;
import supermarket.Customer.*;

public class Supermarket {

    private static final int workingTimeMinutes = 1;

    private final SupermarketEvent marketEvent = new SupermarketEvent();;
    private boolean isOpen;
    private final List<Customer> customers = new ArrayList<>();
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

    public void finishWork() {
        // TODO: show report
    }

    public void configureMarket() {
        if (!this.isOpen) {
            // TODO: add products & prices
        } else {
            // TODO: log err
        }
    }

    public void addCustomer(Customer customer) {
        if (this.isOpen) {
            // TODO: add customer
        } else {
            // TODO: show hint
        }
    }

    public void deleteCustomer(Customer customer) {

    }

    public void runMarketScenario() {

        // todo add products in market
        // todo add open market
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

    private void addRandomCustomer() {
        CustomerType customerType = CustomerType.getByCode((Random.getRandomInt(0, CustomerType.values().length)));
        int cash = Random.getRandomInt(0, 100);
        int bonuses = Random.getRandomInt(0, 10);
        Customer customer = new Customer(customerType, new BigDecimal(cash), new BigDecimal(bonuses));

        int newCustomerId = (customers.size() > 0) ?
                customers.get(customers.size() - 1).getId() + 1 : 1;
        customer.setId(newCustomerId);
        customers.add(customer);

        System.out.println("[" + Datetime.getCurrentDatetime() + "]" + " - new customer #" + customer.getId() + " arrived!");
    }

    private void removeRandomCustomer() {
        if (customers.size() > 0) {
            int rndCustomerIndex = Random.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            customers.remove(rndCustomerIndex);
            System.out.println("[" + Datetime.getCurrentDatetime() + "]" + " - customer #" + rndCustomer.getId() + " came out!");
        }
    }
}
