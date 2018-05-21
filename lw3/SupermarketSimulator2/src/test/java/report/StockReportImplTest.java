package report;

import org.junit.Assert;
import org.junit.Test;
import supermarket.product_stock.ProductStock;
import supermarket.product_stock.ProductStockImpl;
import supermarket.report.Report;
import supermarket.report.StockReportImpl;
import supermarket.stat.StockStat;
import supermarket.stat.StockStatImpl;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class StockReportImplTest extends Assert {

    @Test
    public void stockReport() {
        StockStat stockStat = new StockStatImpl();
        ProductStock productStock = new ProductStockImpl();
        productStock.generateRandomProductStore();
        Map<Integer, Integer> soldProducts = new HashMap<>();

        soldProducts.put(1, 20);
        soldProducts.put(2, 10);
        soldProducts.put(3, 5);

        stockStat.addSoldProducts(soldProducts);
        Report report = new StockReportImpl(stockStat, productStock);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        report.printReport(ps);

        assertEquals(baos.toString(), "---------------------------------------\n" +
                "Stock report (sold products):\n" +
                " - Banan 20 (KG)\n" +
                " - Beer 10 (PIECES)\n" +
                " - Cigaro 5 (PIECES)\n" +
                "---------------------------------------\n" +
                "Total sold: 35\n" +
                "\n" +
                "---------------------------------------\n");
    }
}