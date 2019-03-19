package com.kashanok.part1;

import java.util.*;

/**
 * Created by Raman Kashanok
 */
public class ArrayRandomizer {

    private static Random rand = new Random();

    public static Integer [] getFilledRandomArray() {
        int randomDigit;
        final int arraySize = (rand.nextInt(10) + 5) * 2 + 1;

        List<Integer> intList = new ArrayList<>();

        for (int i = 0; i < arraySize; i++) {
            randomDigit = rand.nextInt(30) + 1;
            intList.add(randomDigit);
            intList.add(randomDigit);
        }
        do {
            randomDigit = rand.nextInt(30) + 1;
        } while (intList.contains(randomDigit));

        intList.add(randomDigit);
        Collections.shuffle(intList);
        return (Integer[]) intList.toArray();
    }
}
