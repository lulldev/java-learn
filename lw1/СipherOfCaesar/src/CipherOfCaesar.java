package net.volgatech.javacore2017;

public class CipherOfCaesar {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("First argument are required!");
            System.exit(-1);
        }

        System.out.println("Hello, Java from " + args[0]);
        for(int i = 1; i < args.length; i++) {
            System.out.print(args[i] + " ");
        }
        System.out.println();

        System.out.println("OS name: " + System.getProperty("os.name"));
        System.out.println("OS version: " + System.getProperty("os.version"));
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("JDK path: " + System.getenv("JAVA_HOME"));
        System.out.println("User path: " + System.getProperty("user.dir"));
    }
}
