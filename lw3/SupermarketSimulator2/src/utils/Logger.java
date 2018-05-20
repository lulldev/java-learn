package utils;

public class Logger {
    public static void show(String description) {
        System.out.println("[" + Datetime.getCurrentDatetime() + "]" + " - " + description);
    }
}
