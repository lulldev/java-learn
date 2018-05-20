package supermarket.report;
import supermarket.stat.StockStat;
import supermarket.product.ProductStock;
import supermarket.stat.StockStatImpl;

import java.util.Map;

public class ReportImpl implements Report {

    private StockStat stockStat = new StockStatImpl();
    private ProductStock productStock = new ProductStock();

    public final void addSoldProducts(Map<Integer, Integer> soldProducts) {
        stockStat.addSoldProducts(soldProducts);
    }

    private final String getSoldProductReport() {
        final String[] outputReportData = {"Stock report (sold products):\n"};
        final int[] totalCount = {0};
        stockStat.getSoldProducts().forEach((productId, productCount) -> {
//            product product = productStock.GetProductById(productId);
            outputReportData[0] += " - " + productId + " - " + productCount + " " + " cccc " + "\n";
            totalCount[0] += productCount;
        });
        outputReportData[0] += "-----------------\n";
        outputReportData[0] += "Total sold: " + totalCount[0] + "\n";
        return outputReportData[0];
    }

    public final void printReport() {
        System.out.println("report:");
        System.out.println("---------------------------------------");
        System.out.println(getSoldProductReport());
        System.out.println("---------------------------------------");
    }
}