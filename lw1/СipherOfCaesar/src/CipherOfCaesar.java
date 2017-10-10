package net.volgatech.javacore2017;

public class CipherOfCaesar {
    public static int ERR_INVALID_PROGRAM_ARGS  = -1;
    public static int ERR_ENCRYPTION_KEY_LENGTH = -2;
    public static int ERR_ENCRYPTION_KEY_TYPE   = -3;
    public static int ERR_ALPHABET_LENGTH       = -4;  
    public static int ERR_MODE_VALUE            = -5;  
    public static int EXIT_SUCCESS              =  0;
    public static int MAX_RU_ALPHABET_LENGHT    = 32;
    public static int MAX_EN_ALPHABET_LENGHT    = 26;

    public static String caesarEncrypt(String textString, int shift) {
        char[] chars = textString.toCharArray();
        for (int i = 0; i < textString.length(); i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                c = (char) (c + (shift % MAX_EN_ALPHABET_LENGHT));
                if (c > 'z') {
                    c = (char) ('a' + (c - 'z') - 1);
                }
                chars[i] = c;
            }
            else if (c >= 'A' && c <= 'Z') {
                c = (char) (c + (shift % MAX_EN_ALPHABET_LENGHT));
                if (c > 'Z') {
                    c = (char) ('A' + (c - 'Z') - 1);
                }
                chars[i] = c;
            }
            else if (c >= 'а' && c <= 'я') {
                c = (char) (c + (shift % MAX_RU_ALPHABET_LENGHT));
                if (c > 'я') {
                    c = (char) ('а' + (c - 'я') - 1);
                }
                chars[i] = c;
            }     
            else if (c >= 'А' && c <= 'Я') {
                c = (char) (c + (shift % MAX_RU_ALPHABET_LENGHT));
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
                c = (char) (c - (shift % MAX_EN_ALPHABET_LENGHT));
                if (c < 'a') {
                    c = (char) ('z' - ('a' - c) + 1);
                }
                chars[i] = c;
            }
            else if (c >= 'A' && c <= 'Z') {
                c = (char) (c - (shift % MAX_EN_ALPHABET_LENGHT));
                if (c < 'A') {
                    c = (char) ('Z' - ('A' - c) + 1);
                }
                chars[i] = c;
            }
            else if (c >= 'а' && c <= 'я') {
                c = (char) (c - (shift % MAX_RU_ALPHABET_LENGHT));
                if (c < 'а') {
                    c = (char) ('я' - ('а' - c) + 1);
                }
                chars[i] = c;
            }     
            else if (c >= 'А' && c <= 'Я') {
                c = (char) (c - (shift % MAX_RU_ALPHABET_LENGHT));
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
            System.exit(ERR_INVALID_PROGRAM_ARGS);
        }

        int key = 0;

        try {
            key = Integer.parseInt(args[1]);
            if (key < 1) {
                System.out.println("Key of encryption must be > 0");
                System.exit(ERR_ENCRYPTION_KEY_LENGTH);
            }
        } catch (NumberFormatException e) {
            System.out.println("Specify integer key for encoding/decoding!");
            System.exit(ERR_ENCRYPTION_KEY_TYPE);
        }

        String alphabet = args[2];

        if (alphabet.length() == 0) {
            System.out.println("Specify filled akphabet for encrypt/decrypt!");
            System.exit(ERR_ALPHABET_LENGTH);
        }

        switch (args[0]) {
        case "-e":
            System.out.println(caesarEncrypt(alphabet, key));
            break;
        case "-d":
            System.out.println(caesarDecrypt(alphabet, key));
            break;
        default:
            System.out.println("Specify mode argument!");
            System.exit(ERR_MODE_VALUE);
            break;
        }
        System.exit(EXIT_SUCCESS);
    }
}
