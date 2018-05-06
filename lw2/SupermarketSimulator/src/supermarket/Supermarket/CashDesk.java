package supermarket.Supermarket;

import com.sun.tools.javac.util.ArrayUtils;
import supermarket.Customer.Customer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class CashDesk {

    private List<Integer> customersQueeIds = new ArrayList<>();
//    private PaymentMethod paymentMethod;
//    private Bill bill;

    public void addCustomerToQuee(int customerId) {

        List<Integer> result = new ArrayList<>();

        for(int currentCustomerId : this.customersQueeIds) {
            if(currentCustomerId != customerId) {
                result.add(currentCustomerId);
            }
        }

        this.customersQueeIds = result;
    }

    public void removeCustomerFromQuee(int customerId) {
        customersQueeIds.add(customerId);
    }
}
