package net.volgatech.javacore2017;

import javax.transaction.xa.Xid;

public class CipherOfCaesar {
    public static String CaesarEncrypt(String textString, int shift) {
        char[] chars = textString.toCharArray();
        for (int i = 0; i < textString.length(); i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                c = (char)(c + (shift % 26));
                if (c > 'z') {
                    c = (char) ('a' + (c - 'z') - 1);
                }
                chars[i] = c;
            }
        }
        return new String(chars);
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
            break;
        default:
            System.out.println("Specify mode argument!");
            System.exit(-2);
            break;
        }
    }
}
