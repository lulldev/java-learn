package supermarket;

import supermarket.supermarket.Supermarket;
import supermarket.supermarket.SupermarketImpl;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        SupermarketImpl supermarket = new SupermarketImpl();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int seconds = 0;
            @Override
            public void run() {
                supermarket.runMarketScenario();
                if (++seconds == (supermarket.getWorkingTimeMinutes() * 60)) {
                    supermarket.closeMarket();
                    supermarket.showReport();
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }
}
