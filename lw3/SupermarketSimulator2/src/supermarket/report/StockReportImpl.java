package supermarket.report;

import supermarket.product.Product;
import supermarket.product.ProductStock;
import supermarket.stat.StockStat;

public class StockReportImpl implements Report {

    private StockStat stockStat;
    private ProductStock productStock;

    public StockReportImpl(StockStat stockStat, ProductStock productStock) {
        this.stockStat = stockStat;
        this.productStock = productStock;
    }

    private final String getSoldProductReport() {
        final String[] outputReportData = {"Stock report (sold products):\n"};
        final int[] totalCount = {0};
        stockStat.getSoldProducts().forEach((productId, productCount) -> {
            outputReportData[0]
                    += " - " + productStock.GetProductById(productId).getProductName()
                    + productCount + " "
                    + "(" + productStock.GetProductById(productId).getProductMeasure() + ")\n";
            totalCount[0] += productCount;
        });
        outputReportData[0] += "-----------------\n";
        outputReportData[0] += "Total sold: " + totalCount[0] + "\n";
        return outputReportData[0];
    }

    public final void printReport() {
        System.out.println("---------------------------------------");
        System.out.println(getSoldProductReport());
        System.out.println("---------------------------------------");
    }
}
