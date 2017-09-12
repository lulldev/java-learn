package net.volgatech.javacore2017;

public class HelloWorld {
  public static void main(String[] args) {
    System.out.println("Hello, Java from");
    System.out.println("OS name: " + System.getProperty("os.name"));
    System.out.println("OS version: " + System.getProperty("os.version"));
    System.out.println("Java version: " + System.getProperty("java.version"));
    System.out.println("Java path: " + System.getProperty("java.home"));
  }
}
