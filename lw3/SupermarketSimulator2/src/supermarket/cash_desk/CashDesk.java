package supermarket.cash_desk;

import supermarket.customer.Customer;
import supermarket.product.ProductStock;
import supermarket.report.Report;
import supermarket.stat.StockStat;

import java.util.List;

public interface CashDesk {
    void addCustomerToQuee(int customerId);
    void removeCustomerFromQuee(int customerId);
    void serveNextCustomer(List<Customer> customers, ProductStock productStock, StockStat stockStat);
}
