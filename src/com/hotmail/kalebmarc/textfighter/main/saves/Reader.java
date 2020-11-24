package com.hotmail.kalebmarc.textfighter.main.saves;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Reader {
    public static Scanner input;

    public static FileReader read(File file) {
        try {
            if (!file.exists())
                return null;
            return new FileReader(file);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return the next line as an integer
     */
    public static int readInt(){
        return Integer.parseInt(input.nextLine());
    }

    /**
     *
     * @return the next line as a boolean
     */
    public static boolean readBoolean(){
        return Boolean.parseBoolean(input.nextLine());
    }

    /**
     *
     * @return the next line as a String
     */
    public static String readString(){
        return input.nextLine();
    }
}
