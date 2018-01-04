package supermarket.Supermarket;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import supermarket.Supermarket.CashDesk;
import supermarket.Supermarket.SupermarketEvent;
import supermarket.Customer.Customer;

public class Supermarket {

    private static final int workingTimeMinutes = 1;

    private SupermarketEvent marketEvent;
    private boolean isOpen;
    private Customer customerList[];
    private CashDesk cashDesk;

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
                // TODO: handle in switch case types
                // TODO: simplify event calls
                // new SupermarketEvent().getNextRandomEvent()
                if (++seconds == (workingTimeMinutes * 60)) {
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }

}
