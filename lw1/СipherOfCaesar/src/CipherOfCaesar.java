package net.volgatech.javacore2017;

public class CipherOfCaesar {
    public static String caesarEncrypt(String textString, int shift) {
        char[] chars = textString.toCharArray();
        for (int i = 0; i < textString.length(); i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                c = (char) (c + (shift % 26));
                if (c > 'z') {
                    c = (char) ('a' + (c - 'z') - 1);
                }
                chars[i] = c;
            }
            else if (c >= 'A' && c <= 'Z') {
                c = (char) (c + (shift % 26));
                if (c > 'Z') {
                    c = (char) ('A' + (c - 'Z') - 1);
                }
                chars[i] = c;
            }
            else if (c >= 'а' && c <= 'я') {
                c = (char) (c + (shift % 32));
                if (c > 'я') {
                    c = (char) ('а' + (c - 'я') - 1);
                }
                chars[i] = c;
            }     
            else if (c >= 'А' && c <= 'Я') {
                c = (char) (c + (shift % 32));
                if (c > 'Я') {
                    c = (char) ('А' + (c - 'Я') - 1);
                }
                chars[i] = c;
            }            
        }
        return new String(chars);
    }

    public static String caesarDecrypt(String textString, int shift) {
        char[] chars = textString.toCharArray();
        for (int i = 0; i < textString.length(); i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                c = (char) (c + (shift % 26));
                if (c < 'a') {
                    c = (char) ('z' - ('a' - c) + 1);
                }
                chars[i] = c;
            }
            else if (c >= 'A' && c <= 'Z') {
                c = (char) (c + (shift % 26));
                if (c < 'А') {
                    c = (char) ('Z' - ('A' - c) + 1);
                }
                chars[i] = c;
            }
            else if (c >= 'а' && c <= 'я') {
                c = (char) (c + (shift % 32));
                if (c < 'а') {
                    c = (char) ('я' - ('а' - c) + 1);
                }
                chars[i] = c;
            }     
            else if (c >= 'А' && c <= 'Я') {
                c = (char) (c + (shift % 32));
                if (c < 'А') {
                    c = (char) ('Я' - ('А' - c) + 1);
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
            System.out.println(caesarEncrypt(args[2], key));
            break;
        case "-d":
            System.out.println(caesarDecrypt(args[2], key));
            break;
        default:
            System.out.println("Specify mode argument!");
            System.exit(-2);
            break;
        }
    }
}
