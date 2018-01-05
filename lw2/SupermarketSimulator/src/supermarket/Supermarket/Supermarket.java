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

    private SupermarketEvent marketEvent;
    private boolean isOpen;
    private List<Customer> customers;
    private CashDesk cashDesk;

    public void Supermarket() {
        this.marketEvent = new SupermarketEvent();
    }

    public void openMarket() {
        this.isOpen = true;
    }

    public void closeMarket() {
        this.isOpen = false;
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
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int seconds = 0;
            @Override
            public void run() {
                // TODO: init once and calls many this.marketEvent
                switch (new SupermarketEvent().getNextRandomEvent()) {
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
                if (++seconds == (workingTimeMinutes * 60)) {
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }

}
