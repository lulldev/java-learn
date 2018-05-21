package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Datetime {
    /**
     * @return String current datetime
     */
    public static String getCurrentDatetime() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
