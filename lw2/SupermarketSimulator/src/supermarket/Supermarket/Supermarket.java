package supermarket.Supermarket;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Map;

import com.sun.tools.javac.util.ArrayUtils;
import utils.RandomUtil;
import utils.Datetime;

import supermarket.Customer.*;
import supermarket.Product.*;

public class Supermarket {

    private static final int workingTimeMinutes = 1;

    private final SupermarketEvent marketEvent = new SupermarketEvent();;
    private boolean isOpen;
    private final List<Customer> customers = new ArrayList<>();
    private final ProductStock productStock = new ProductStock();
    private CashDesk cashDesk;

    // todo add logger

    public void openMarket() {
        this.isOpen = true;
    }
    public void closeMarket() {
        this.isOpen = false;
    }
    public int getWorkingTimeMinutes() {
        return workingTimeMinutes;
    }

    public void runMarketScenario() {

        if (!this.isOpen) {
            openMarket();
            logger("market is opened!");
            configureMarket();
        }

        // TODO: init once and calls many this.marketEvent
        switch (marketEvent.getNextRandomEvent()) {
            case SupermarketEvent.EVENT_CUSTOMER_CAME_IN:
                addRandomCustomer();
                break;
            case SupermarketEvent.EVENT_CUSTOMER_CAME_OUT:
                removeRandomCustomer();
                break;
            case SupermarketEvent.EVENT_CUSTOMER_PUT_IN_BUSKET:
                putInRandomProductForCustomer();
                break;
            case SupermarketEvent.EVENT_CUSTOMER_LAID_BUSKET:
                cameOutRandomCustomerProduct();
                break;
            case SupermarketEvent.EVENT_CASHDESK_VALID_PAY:
                break;
            case SupermarketEvent.EVENT_CASHDESK_WRONG_PAY:
                break;
        }
    }

    private void configureMarket() {
        logger("Add products to Supermarket stock");
        productStock.GenerateRandomProductStore();
    }

    private void addRandomCustomer() {
        CustomerType customerType = CustomerType.getByCode((RandomUtil.getRandomInt(0, CustomerType.values().length)));
        int cash = RandomUtil.getRandomInt(0, 100);
        int bonuses = RandomUtil.getRandomInt(0, 10);
        Customer customer = new Customer(customerType, new BigDecimal(cash), new BigDecimal(bonuses));

        int newCustomerId = (customers.size() > 0) ?
                customers.get(customers.size() - 1).getId() + 1 : 1;
        customer.setId(newCustomerId);
        customers.add(customer);
        logger("new customer #" + customer.getId() + " arrived!");
    }

    private void removeRandomCustomer() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            customers.remove(rndCustomerIndex);
            logger("customer #" + rndCustomer.getId() + " came out!");
        }
    }

    private void putInRandomProductForCustomer() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);

            List<Product> productList = productStock.GetProductList();
            int rndProductIndex = RandomUtil.getRandomInt(0, productList.size());
            // todo: возможно сделать вычет по количеству или measure
            if (productStock.deductProduct(rndProductIndex)) {
                rndCustomer.putProductInBasket(rndProductIndex);
                Product product = productStock.GetProductById(rndProductIndex);
                logger("customer #" + rndCustomer.getId() + " put in basket: \n"
                        + product.toString());
            }
        }
    }

    private void cameOutRandomCustomerProduct() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            Basket customerBasket = rndCustomer.getBasket();
            int basketSize = customerBasket.BasketSize();

            if (basketSize > 0) {
                int rndProductIndex = RandomUtil.getRandomFromArray(customerBasket.ToIntArray());
                rndCustomer.cameOutProductInBasket(rndProductIndex);
                productStock.returnProduct(rndProductIndex);
                Product product = productStock.GetProductById(rndProductIndex);
                logger("customer #" + rndCustomer.getId() + " came out from basket: \n"
                        + product.toString());
            }
        }
    }

    // todo: logger to singleton
    private void logger(String action) {
        System.out.println("[" + Datetime.getCurrentDatetime() + "]" + " - " + action);
    }
}
