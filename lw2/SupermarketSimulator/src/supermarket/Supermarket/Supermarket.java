package supermarket.Supermarket;

import supermarket.Customer.Customer;
import supermarket.Supermarket.CashDesk;

public class Supermarket {

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

}
