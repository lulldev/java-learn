package supermarket.Supermarket;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.math.BigDecimal;

import supermarket.Supermarket.CashDesk;
import supermarket.Supermarket.SupermarketEvent;
import supermarket.Customer.*;

public class Supermarket {

    private static final int workingTimeMinutes = 1;

    private SupermarketEvent marketEvent = new SupermarketEvent();;
    private boolean isOpen;
    private List<Customer> customers;
    private CashDesk cashDesk;

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
        CustomerType customerType = CustomerType.getByCode((int) (Math.random() * (CustomerType.values().length - 0)) + 0);
        int cash = (int) (Math.random() * (100 - 1)) + 1;
        int bonuses = (int) (Math.random() * (10 - 0)) + 0;
        Customer customer = new Customer(customerType, new BigDecimal(cash), new BigDecimal(bonuses));
        System.out.println("new customer");
        System.out.println(customer.getCash());
    }
}
