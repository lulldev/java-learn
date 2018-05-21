package supermarket.cash_desk;

import supermarket.customer.Customer;
import supermarket.stat.StockStat;
import supermarket.product_stock.ProductStock;

import java.util.List;

public interface CashDesk {
    void addCustomerToQuee(int customerId);
    void removeCustomerFromQuee(int customerId);
    void serveNextCustomer(List<Customer> customers, ProductStock productStock, StockStat stockStat);
}
