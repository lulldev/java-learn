package utils;

public class Logger {
    public static void message(String description, boolean isShowTimestamp) {
        String datetime = isShowTimestamp ? "[" + Datetime.getCurrentDatetime() + "]" + " - " : "";
        System.out.println(datetime + description);
    }
}
