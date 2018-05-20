package supermarket.supermarket;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import supermarket.cash_desk.CashDeskImpl;
import supermarket.report.Report;
import supermarket.report.ReportImpl;
import supermarket.customer.Customer;
import supermarket.customer.CustomerImpl;
import supermarket.customer.CustomerType;
import supermarket.product.Product;
import supermarket.product.ProductStock;
import utils.Logger;
import utils.RandomUtil;

public class SupermarketImpl implements Supermarket {

    private static final int workingTimeMinutes = 1;

    private final SupermarketEvent marketEvent = new SupermarketEvent();;
    private boolean isOpen;
    private final List<Customer> customers = new ArrayList<>();
    private final ProductStock productStock = new ProductStock();
    private CashDeskImpl cashDesk = new CashDeskImpl();
    private Report report = new ReportImpl();

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
            Logger.show("market is opened!");
            configureMarket();
        }

        switch (marketEvent.getNextRandomEvent()) {
            case SupermarketEvent.EVENT_CUSTOMER_CAME_IN:
                addRandomCustomer();
                break;
            case SupermarketEvent.EVENT_CUSTOMER_PUT_IN_BUSKET:
                putInRandomProductForCustomer();
                break;
            case SupermarketEvent.EVENT_CUSTOMER_JOIN_QUEE:
                randomCustomerJoinQuee();
                break;
            case SupermarketEvent.EVENT_CUSTOMER_LEFT_QUEE:
                randomCustomerLeftQuee();
                break;
            case SupermarketEvent.EVENT_CUSTOMER_SERVE_NEXT:
                serveNextCustomerFromQuee();
                break;
        }
    }

    private void configureMarket() {
        Logger.show("Add products to supermarket stock");
        productStock.GenerateRandomProductStore();
    }

    private void addRandomCustomer() {
        CustomerType customerType = CustomerType.getByCode((RandomUtil.getRandomInt(0, CustomerType.values().length)));
        int cash = RandomUtil.getRandomInt(50, 500);
        int bonuses = (customerType == CustomerType.Retired) ?
                RandomUtil.getRandomInt(0, 50) : 0;

        CustomerImpl customer = new CustomerImpl(
                customerType,
                new BigDecimal(cash),
                bonuses
        );

        customers.add(customer);
        Logger.show("new customer (id: " + customer.getId() + ") arrived!");
    }

    private void removeRandomCustomer() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            customers.remove(rndCustomerIndex);
            Logger.show("customer (id: " + rndCustomer.getId() + ") came out!");
        }
    }

    private void putInRandomProductForCustomer() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);

            List<Product> productList = productStock.GetProductList();
            int rndProductIndex = RandomUtil.getRandomInt(0, productList.size());
            int rndProductCount = RandomUtil.getRandomInt(1, 5);

            if (productStock.deductProduct(rndProductIndex, rndProductCount)) {
                rndCustomer.putProductInBasket(rndProductIndex, rndProductCount);
                Product product = productStock.GetProductById(rndProductIndex);
                Logger.show("customer (id: " + rndCustomer.getId() + ") put in basket: "
                        + product.toString() + " (" + rndProductCount + " " + product.getProductMeasure() + ")");
            }
        }
    }

    private void randomCustomerJoinQuee() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            if (rndCustomer.issetProductsInBasket()) {
                cashDesk.addCustomerToQuee(rndCustomer.getId());
                Logger.show("customer (id: " + rndCustomer.getId() + ") join to query");
            }
        }
    }

    private void randomCustomerLeftQuee() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            cashDesk.removeCustomerFromQuee(rndCustomer.getId());
            Logger.show("customer (id: " + rndCustomer.getId() + ") left to cash desk quee");
        }
    }

    private void serveNextCustomerFromQuee() {
        if (customers.size() > 0) {
            cashDesk.serveNextCustomer(customers, productStock, report);
        }
    }

    public void showReport() {
        this.report.printReport();
    }
    
}
