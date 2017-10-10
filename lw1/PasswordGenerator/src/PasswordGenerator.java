package net.volgatech.javacore2017;

public class PasswordGenerator {
    public static String PassGenerator(int length, String alphabet) {
        char[] alphabetChars = alphabet.toCharArray();
        char[] resultPassword;
        resultPassword = new char[length];
        for (int i = 0; i < length; i++) {
            resultPassword[i] = alphabetChars[(int)(Math.random() * alphabet.length())];
        }
        return new String(resultPassword);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Specify the arguments for the program!");
            System.out.println("PasswordGenerator <length> <alphabet_string>");
            System.exit(-1);
        }

        int length = 0;

        try {
            length = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Specify integer length");
            System.exit(-2);
        }
        System.out.println(PassGenerator(length, args[1]));
        System.exit(0);
    }
}
