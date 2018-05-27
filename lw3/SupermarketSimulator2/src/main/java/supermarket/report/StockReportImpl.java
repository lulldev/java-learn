package supermarket.report;

import supermarket.product_stock.ProductStock;
import supermarket.stat.StockStat;

import java.io.FileDescriptor;
import java.io.PrintStream;
import java.math.BigDecimal;

public class StockReportImpl implements Report {

    private StockStat stockStat;
    private ProductStock productStock;
    private BigDecimal[] totalPrice = {new BigDecimal(0)};

    public StockReportImpl(StockStat stockStat, ProductStock productStock) {
        this.stockStat = stockStat;
        this.productStock = productStock;
    }

    private final String getSoldProductReport() {
        String[] outputReportData = {"Stock report (sold products):\n"};
        int[] totalCount = {0};
        stockStat.getSoldProducts().forEach((productId, productCount) -> {
            BigDecimal price = productStock.getProductById(productId).getProductPrice();
            outputReportData[0]
                    += " - " + productStock.getProductById(productId).getProductName()
                    + " " + productCount + " "
                    + "(" + productStock.getProductById(productId).getProductMeasure() + ") - "
                    + " price: " + price + " "
                    + " total price: " + price.multiply(new BigDecimal(productCount)) + "\n";
            totalCount[0] += productCount;

            BigDecimal calculateTotal = productStock
                    .getProductById(productId)
                    .getProductPrice()
                    .multiply(new BigDecimal(productCount));
            totalPrice[0] = totalPrice[0].add(calculateTotal);
        });
        outputReportData[0] += "---------------------------------------\n";
        outputReportData[0] += "Total sold: " + totalCount[0] + "\n";
        outputReportData[0] += "Total income: " + totalPrice[0] + "\n";
        return outputReportData[0];
    }

    public final void printReport(PrintStream output) {
        output.println("---------------------------------------");
        output.println(getSoldProductReport());
        output.println("---------------------------------------");
    }
}
