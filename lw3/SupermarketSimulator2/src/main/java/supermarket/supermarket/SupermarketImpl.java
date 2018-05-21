package supermarket.supermarket;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import supermarket.cash_desk.CashDeskImpl;
import supermarket.product_stock.ProductStock;
import supermarket.product_stock.ProductStockImpl;
import supermarket.report.Report;
import supermarket.customer.Customer;
import supermarket.customer.CustomerImpl;
import supermarket.customer.CustomerType;
import supermarket.product.Product;
import supermarket.report.StockReportImpl;
import supermarket.stat.StockStat;
import supermarket.stat.StockStatImpl;
import utils.Logger;
import utils.RandomUtil;

public class SupermarketImpl implements Supermarket {

    private static final int workingTimeMinutes = 1;

    private final SupermarketEvent marketEvent = new SupermarketEvent();;
    private boolean isOpen;
    private final List<Customer> customers = new ArrayList<>();
    private final ProductStock productStock = new ProductStockImpl();
    private CashDeskImpl cashDesk = new CashDeskImpl();
    private StockStat stockStat = new StockStatImpl();

    public void openMarket() {
        this.isOpen = true;
    }
    public void closeMarket() {
        this.isOpen = false;
    }
    public int getWorkingTimeMinutes() {
        return workingTimeMinutes;
    }
    public void showReport() {
        Report stockReport = new StockReportImpl(this.stockStat, this.productStock);
        stockReport.printReport(System.out);
    }

    public void runMarketScenario() {

        if (!this.isOpen) {
            openMarket();
            Logger.message("market is opened!", true);
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
        Logger.message("Add products to supermarket stock", true);
        productStock.generateRandomProductStore();
    }

    private void addRandomCustomer() {
        CustomerType customerType = CustomerType.getByCode((RandomUtil.getRandomInt(0, CustomerType.values().length)));
        int cash = RandomUtil.getRandomInt(50, 200);
        int cardCash = RandomUtil.getRandomInt(50, 500);
        int bonuses = (customerType == CustomerType.Retired) ?
                RandomUtil.getRandomInt(0, 50) : 0;

        CustomerImpl customer = new CustomerImpl(
                customerType,
                new BigDecimal(cash),
                new BigDecimal(cardCash),
                bonuses
        );

        customers.add(customer);
        Logger.message("new customer (id: " + customer.getId() + ") arrived!", true);
    }

    private void removeRandomCustomer() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            customers.remove(rndCustomerIndex);
            Logger.message("customer (id: " + rndCustomer.getId() + ") came out!", true);
        }
    }

    private void putInRandomProductForCustomer() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);

            List<Product> productList = productStock.getProductList();
            int rndProductIndex = RandomUtil.getRandomInt(0, productList.size());
            int rndProductCount = RandomUtil.getRandomInt(1, 5);

            if (productStock.deductProduct(rndProductIndex, rndProductCount)) {
                rndCustomer.putProductInBasket(rndProductIndex, rndProductCount);
                Product product = productStock.getProductById(rndProductIndex);
                Logger.message("customer (id: " + rndCustomer.getId() + ") put in basket: "
                        + product.toString() + " (" + rndProductCount + " " + product.getProductMeasure() + ")"
                        , true);
            }
        }
    }

    private void randomCustomerJoinQuee() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            if (rndCustomer.issetProductsInBasket()) {
                cashDesk.addCustomerToQuee(rndCustomer.getId());
                Logger.message("customer (id: " + rndCustomer.getId() + ") join to query", true);
            }
        }
    }

    private void randomCustomerLeftQuee() {
        if (customers.size() > 0) {
            int rndCustomerIndex = RandomUtil.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            cashDesk.removeCustomerFromQuee(rndCustomer.getId());
            Logger.message("customer (id: " + rndCustomer.getId() + ") left to cash desk quee", true);
        }
    }

    private void serveNextCustomerFromQuee() {
        if (customers.size() > 0) {
            cashDesk.serveNextCustomer(customers, productStock, stockStat);
        }
    }


}
