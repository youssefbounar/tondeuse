package com.mowitnow.tondeuse.shared.helpers;

import com.mowitnow.tondeuse.shared.enums.Instructions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {

    // Method to check if all characters exist in the enum
    public static <T extends Enum<T>> boolean containsAllInEnum(char[] charArray, Class<T> enumClass) {
        for (char c : charArray) {
            if (!contains(c, enumClass)) {
                return false;
            }
        }
        return true;
    }


    // Utility method to check if a character exists in the enum
    public static <T extends Enum<T>> boolean contains(char c, Class<T> enumClass) {
        for (T enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.name().charAt(0) == c) {
                return true;
            }
        }
        return false;
    }

    public static void checkIfInstructionsExist(String instructions) {
        System.out.println("instructions : " + instructions);
        char[] charArray = instructions.toCharArray();


        // Check if all elements exist in the enum using streams
        boolean allExistInEnum = Utils.containsAllInEnum(charArray, Instructions.class);
//				allExistInEnum = true;
        if (!allExistInEnum) throw new RuntimeException("charactre not accepted ... ");
    }

    public static Scanner openFile(String fileName) {
        try {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);
            if (!scanner.hasNext()) {
                System.out.println("The file is empty.");
                throw new RuntimeException("The file is empty..");
            } else {
                return scanner;
            }
        } catch (FileNotFoundException e) {
            // Print error message if the file is not found
            throw new RuntimeException("File not found.");
        }
    }

    public static int checkIntValue(Scanner scanner) {
        int maxX = 0;
        if (scanner.hasNextInt()) {
            maxX = scanner.nextInt();
            return maxX;
        } else {
            System.out.println("Next input is not an integer.");
            String value = scanner.next();
            // Consume the invalid input to avoid it being read again
            throw new RuntimeException("Value not accepted : " + value);
        }
    }
}
