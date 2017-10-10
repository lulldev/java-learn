package net.volgatech.javacore2017;

public class PasswordGenerator {
    public static int ERR_INVALID_PROGRAM_ARGS    = -1;
    public static int ERR_INVALID_PASSWORD_LENGTH = -2;
    public static int ERR_INVALID_PASSWORD_TYPE   = -3;
    public static int EXIT_SUCCESS                =  0;

    public static String passGenerator(int length, String alphabet) {
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
            System.exit(ERR_INVALID_PROGRAM_ARGS);
        }

        int length = 0;

        try {
            length = Integer.parseInt(args[0]);
            if (length < 1) {
                System.out.println("Invalid password length");
                System.exit(ERR_INVALID_PASSWORD_LENGTH);
            }
        } catch (NumberFormatException e) {
            System.out.println("Specify integer length");
            System.exit(ERR_INVALID_PASSWORD_TYPE);
        }
        System.out.println(passGenerator(length, args[1]));
        System.exit(EXIT_SUCCESS);
    }
}
