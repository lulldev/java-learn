package supermarket.Supermarket;
import supermarket.Product.Product;
import supermarket.Supermarket.StockStat;
import supermarket.Product.ProductStock;

import java.util.Map;

public class Report {

    private StockStat stockStat = new StockStat();
    private ProductStock productStock = new ProductStock();

    public final void addSoldProducts(Map<Integer, Integer> soldProducts) {
        stockStat.addSoldProducts(soldProducts);
    }

    public final String getSoldProductReport() {
        final String[] outputReportData = {"Stock report (sold products):\n"};
        final int[] totalCount = {0};
        stockStat.getSoldProducts().forEach((productId, productCount) -> {
//            Product product = productStock.GetProductById(productId);
            outputReportData[0] += " - " + productId + " - " + productCount + " " + " cccc " + "\n";
            totalCount[0] += productCount;
        });
        outputReportData[0] += "-----------------\n";
        outputReportData[0] += "Total sold: " + totalCount[0] + "\n";
        return outputReportData[0];
    }

    public final void printReport() {
        System.out.println("Report:");
        System.out.println("---------------------------------------");
        System.out.println(getSoldProductReport());
        System.out.println("---------------------------------------");
    }
}
