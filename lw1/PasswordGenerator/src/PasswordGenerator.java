package net.volgatech.javacore2017;

public class PasswordGenerator {
    public static String PasswordGenerator(int length, String alphabet) {
        char[] alphabetChars = alphabet.toCharArray();
        for (int i = 0; i < length; i++) {
            char c = alphabetChars[i];     
        }
        return new String(alphabetChars);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Specify the arguments for the program!");
            System.out.println("CaesarCrypt <mode> <key> <target_string>");
            System.exit(-1);
        }

        int key = 0;

        try {
            key = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Specify integer key for encoding/decoding!");
            System.exit(-3);
        }

        switch (args[0]) {
        case "-e":
            System.out.println(CaesarEncrypt(args[2], key));
            break;
        case "-d":
            System.out.println(CaesarDecrypt(args[2], key));
            break;
        default:
            System.out.println("Specify mode argument!");
            System.exit(-2);
            break;
        }
    }
}
