package com.thg.accelerator23.connectn.ai.hamyal;
import java.lang.reflect.*;
import java.math.BigInteger;

public class Main2 {
    public static void main(String[] args) {

        int[][] myArray = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        for (int[] col: myArray) {
            for (int entry: col) {
                System.out.println(entry);
            }
        }

        System.out.println(myArray[0][2]);

    }
}
