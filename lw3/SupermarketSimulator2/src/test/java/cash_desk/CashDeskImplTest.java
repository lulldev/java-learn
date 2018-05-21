package cash_desk;

import org.junit.Assert;
import org.junit.Test;
import supermarket.cash_desk.CashDeskImpl;
import supermarket.customer.Customer;
import supermarket.product_stock.ProductStock;
import supermarket.stat.StockStat;
import java.util.ArrayList;
import java.util.List;


class MockCashDesk extends CashDeskImpl {
    private boolean isNextCustomerServe = false;
    private List<Integer> customersQueeIds = new ArrayList<>();

    public void addCustomerToQuee(int customerId) {
        customersQueeIds.add(customerId);
    }

    public void removeCustomerFromQuee(int customerId) {
        List<Integer> result = new ArrayList<>();

        for (int currentCustomerId : this.customersQueeIds) {
            if (currentCustomerId != customerId) {
                result.add(currentCustomerId);
            }
        }
        this.customersQueeIds = result;
    }

    public void serveNextCustomer(List<Customer> customers, ProductStock productStock, StockStat stockStat) {
        if (customersQueeIds.size() > 0) {
            isNextCustomerServe = true;
        }
        else {
            isNextCustomerServe = false;
        }
    }

    public int getQueeCount() {
        return customersQueeIds.size();
    }

    public boolean isNextCustomerServe() {
        return isNextCustomerServe;
    }
}

public class CashDeskImplTest extends Assert {

    private MockCashDesk cashDesk = new MockCashDesk();

    @Test
    public void addCustomerToQuee() {
        cashDesk.addCustomerToQuee(1);
        assertEquals(cashDesk.getQueeCount(), 1);
        cashDesk.addCustomerToQuee(4);
        assertEquals(cashDesk.getQueeCount(), 2);
    }

    @Test
    public void removeCustomerFromQuee() {
        cashDesk.addCustomerToQuee(1);
        cashDesk.addCustomerToQuee(4);
        assertEquals(cashDesk.getQueeCount(), 2);
        cashDesk.removeCustomerFromQuee(1);
        assertEquals(cashDesk.getQueeCount(), 1);
    }


}
