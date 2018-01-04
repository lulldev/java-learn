package supermarket.Supermarket;

import java.util.Timer;
import java.util.TimerTask;

import supermarket.Customer.Customer;
import supermarket.Supermarket.CashDesk;

public class Supermarket {

    private static final int workingTimeMinutes = 1;

    private boolean isOpen;
    private Customer customerList[];
    private CashDesk cashDesks[];

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
            int n = 0;
            @Override
            public void run() {
                System.out.println(n);
                if (++n == (workingTimeMinutes * 60)) {
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }

}
