package utils;

import org.junit.Assert;
import org.junit.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsTest extends Assert {

    @Test
    public void getCurrentDatetime() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        assertTrue(Datetime.getCurrentDatetime().contains(dateFormat.format(date)));
    }

    @Test
    public void randomFunctions() {
        int rndNumer = RandomUtil.getRandomInt(0, 10);
        assertTrue(rndNumer >= 0 && rndNumer <= 10);
        int rndNumer2 = RandomUtil.getRandomInt(10, 10);
        assertEquals(rndNumer2, 10);

    }
}
