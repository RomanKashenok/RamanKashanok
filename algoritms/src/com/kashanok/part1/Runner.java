package com.kashanok.part1;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Raman Kashanok
 */
public class Runner {
    public static void main(String[] args) {
//        final Integer[] filledRandomArray = ArrayRandomizer.getFilledRandomArray();
        final int [] userIntArray;
        final Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int arraySize;
        System.out.println("Enter array size N.");
        do {
            System.out.println("N should be  odd: ( N%2 = 1 )!");
            arraySize = sc.nextInt();
        } while (arraySize % 2 == 0);
        userIntArray = new int[arraySize];

        do {
            System.out.println("type " + arraySize%2 + " NON REPEATABLE digits to fill array, split them with comma (3,5,6,3,8,2");
            String digits = sc.nextLine();
            digits = digits + "," + digits;
            try {
                final String[] splitedIntArray = digits.split("[, ]+");
                if (userIntArray.length != splitedIntArray.length + 1) continue;
            } catch (Exception e) {
                System.out.println("ENTER VALID NUMBERS PLEASE!!!");
            }
        } while (true);

    }
}
