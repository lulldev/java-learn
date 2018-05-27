package net.volgatech.javacore2017;

public class TypesTable {

    public static void main(String [] args) {

        String ROW_FORMAT = "%-15s %-28s %-32s %-4s%n";

        System.out.printf(ROW_FORMAT, "Long", Long.MIN_VALUE, Long.MAX_VALUE, Long.SIZE);
        System.out.printf(ROW_FORMAT, "Integer", Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.SIZE);
        System.out.printf(ROW_FORMAT, "Short", Short.MIN_VALUE, Short.MAX_VALUE, Short.SIZE);
        System.out.printf(ROW_FORMAT, "Byte", Byte.MIN_VALUE, Byte.MAX_VALUE, Byte.SIZE);
        System.out.printf(ROW_FORMAT, "Double", Double.MIN_VALUE, Double.MAX_VALUE, Double.SIZE);
        System.out.printf(ROW_FORMAT, "Float", Float.MIN_VALUE, Float.MAX_VALUE, Float.SIZE);
    }
}
