package supermarket;

import supermarket.Supermarket.Supermarket;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int seconds = 0;
            @Override
            public void run() {
                supermarket.runMarketScenario();
                if (++seconds == (supermarket.getWorkingTimeMinutes() * 60)) {
                    timer.cancel();
                }
            }
        }, 1000, 1000);

        supermarket.closeMarket();
        // todo: logger(supermarket.showReport());
    }
}
