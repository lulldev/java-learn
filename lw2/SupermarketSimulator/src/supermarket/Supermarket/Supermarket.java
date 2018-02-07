package supermarket.Supermarket;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.math.BigDecimal;

import supermarket.Supermarket.CashDesk;
import supermarket.Supermarket.SupermarketEvent;
import supermarket.Customer.Customer;

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
                Customer customer = new Customer("typesdsdsd", new BigDecimal(100), new BigDecimal(100));
                System.out.println("new customer");
                System.out.println(customer.getCash());
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
}
