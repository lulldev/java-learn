package net.volgatech.javacore2017;

import javax.transaction.xa.Xid;

public class CipherOfCaesar 
{
    public static String CaesarCrypt(String textString, int shift, boolean isDecode)
    {
        char[] chars = textString.toCharArray();
        for (int i = 0; i < textString.length(); i++)
        {
            char c = chars[i];
            if (c >= 32 && c <= 127)
            {
                int x = c - 32;
                if (isDecode)
                {
                    x -= shift * 2;
                }
                x = (x + shift) % 96;
                if (x < 0)
                {
                    x += 96;
                }
                chars[i] = (char) (x + 32);
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) 
    {
        if (args.length != 3) 
        {
            System.out.println("Specify the arguments for the program!");
            System.out.println("CaesarCrypt <mode> <key> <target_string>");
            System.exit(-1);
        }

        boolean isDecode = false;
        switch (args[0])
        {
            case "-e":
                isDecode = false;
                break;
            case "-d":
                isDecode = true;
                break;
            default:
                System.out.println("Specify mode argument!");
                System.exit(-2);
                break;    
        }

        int key = 0;
        
        try
        {
            key = Integer.parseInt(args[1]);
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Specify integer key for encoding/decoding!");
            System.exit(-3);
        }
        System.out.println(CaesarCrypt(args[2], key, isDecode));
    }
}
