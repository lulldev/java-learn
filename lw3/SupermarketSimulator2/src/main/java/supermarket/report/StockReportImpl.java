package supermarket.report;

import supermarket.product_stock.ProductStock;
import supermarket.stat.StockStat;

import java.io.FileDescriptor;
import java.io.PrintStream;

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
                    += " - " + productStock.getProductById(productId).getProductName()
                    + " " + productCount + " "
                    + "(" + productStock.getProductById(productId).getProductMeasure() + ")\n";
            totalCount[0] += productCount;
        });
        outputReportData[0] += "---------------------------------------\n";
        outputReportData[0] += "Total sold: " + totalCount[0] + "\n";
        return outputReportData[0];
    }

    public final void printReport(PrintStream output) {
        output.println("---------------------------------------");
        output.println(getSoldProductReport());
        output.println("---------------------------------------");
    }
}
