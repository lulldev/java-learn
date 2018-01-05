package supermarket.Supermarket;

import java.util.concurrent.ThreadLocalRandom;

public class SupermarketEvent {

    public static final int EVENT_CUSTOMER_CAME_IN          = 1;
    public static final int EVENT_CUSTOMER_CAME_OUT         = 2;
    public static final int EVENT_CUSTOMER_PUT_IN_BUSKET    = 3;
    public static final int EVENT_CUSTOMER_LAID_BUSKET      = 4;
    public static final int EVENT_CASHDESK_VALID_PAY        = 5;
    public static final int EVENT_CASHDESK_WRONG_PAY        = 6;

    private final int[] eventPriorityRange = {
            EVENT_CUSTOMER_CAME_IN,
            EVENT_CUSTOMER_CAME_IN,
            EVENT_CUSTOMER_CAME_IN,
            EVENT_CUSTOMER_CAME_OUT,
            EVENT_CUSTOMER_PUT_IN_BUSKET,
            EVENT_CUSTOMER_PUT_IN_BUSKET,
            EVENT_CUSTOMER_PUT_IN_BUSKET,
            EVENT_CUSTOMER_LAID_BUSKET,
            EVENT_CASHDESK_VALID_PAY,
            EVENT_CASHDESK_VALID_PAY,
            EVENT_CASHDESK_WRONG_PAY
    };

    public final int getNextRandomEvent() {
        return this.eventPriorityRange[ThreadLocalRandom.current().nextInt(0, 10 + 1)];
    }

}
