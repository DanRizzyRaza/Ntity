package com.thg.accelerator23.connectn.ai.hamyal;
import java.lang.reflect.*;
import java.math.BigInteger;

public class Main2 {
    public static void main(String[] args) {

        long test = 0b10000000000000000000000000000000;

        System.out.println(Long.toString(test,2));

    }
}

////diag
//                counterArray[5][0] = Counter.O;
//                counterArray[6][1] = Counter.O;
//                counterArray[7][2] = Counter.O;
//                counterArray[8][3] = Counter.O;
//
//                //neg-diag
//                counterArray[0][3] = Counter.O;
//                counterArray[1][2] = Counter.O;
//                counterArray[2][1] = Counter.O;
//                counterArray[3][0] = Counter.O;
//
//                //neg-diag 2
//                counterArray[9][0] = Counter.O;
//                counterArray[8][1] = Counter.O;
//                counterArray[7][2] = Counter.O;
//                counterArray[6][3] = Counter.O;
//
//                // else
//                counterArray[4][0] = Counter.O;
//                counterArray[5][3] = Counter.X;
//                counterArray[7][1] = Counter.X;
//                counterArray[9][7] = Counter.X;


//    long[] shift10 = bitBoardShift(test, 10);
//    long[] shift20 = bitBoardShift(test, 20);
//    long[] shift30 = bitBoardShift(test, 30);

//    System.out.println("0 shift");
//    System.out.println(String.format("%" + 26 + "s", Long.toBinaryString(test[1])).replace(' ', '0') + String.format("%" + 64 + "s", Long.toBinaryString(test[0])).replace(' ', '0'));
//    System.out.println("000000000000001000000000100000000010000000001000000000000000000000000000000000000000000000");
//    System.out.println("1 shift");
//    System.out.println(String.format("%" + 56 + "s", Long.toBinaryString(shift10[1])).replace(' ', '0') + String.format("%" + 64 + "s", Long.toBinaryString(shift10[0])).replace(' ', '0'));
//    System.out.println("2 shift");
//    System.out.println(String.format("%" + 56 + "s", Long.toBinaryString(shift20[1])).replace(' ', '0') + String.format("%" + 64 + "s", Long.toBinaryString(shift20[0])).replace(' ', '0'));
//    System.out.println("3 shift");
//    System.out.println(String.format("%" + 56 + "s", Long.toBinaryString(shift30[1])).replace(' ', '0') + String.format("%" + 64 + "s", Long.toBinaryString(shift30[0])).replace(' ', '0'));
//    System.out.println(String.format("%" + 50 + "s", "").replace(' ', '0'));

//    long[] trial = bitBoardShift(test, 0);
//    System.out.println(Long.toBinaryString(test[0]));
//    System.out.println(Long.toBinaryString(test[1]));
////    System.out.println("shifted");
//    System.out.println(Long.toBinaryString(trial[0]));
//    System.out.println(Long.toBinaryString(trial[1]));

// Bit board shift v1
//    public long[][] bitBoardShift(long[][] bitBoard, int player, int n) { // 0 = home, 1 = away
////        long beforeLongerLong = bitBoard[player][0];
////        long beforeShorterLong = bitBoard[player][1];
////        int longerMovedToShort = (int) (beforeLongerLong >>> (64 - n));  //(1 << n) - 1;
////        long afterLongerLong = beforeLongerLong >> n << n; // take & of complement?
////        long afterShorterShort = (beforeShorterLong << n) | longerMovedToShort ;
//
//        long afterLongerLong = bitBoard[player][0] >> n << n;
//        long afterShorterShort = (bitBoard[player][1] << n) | (bitBoard[player][0] >> (64 - n));
//
//        if (player == 0) {
//            return new long[][]{{afterLongerLong, afterShorterShort}, bitBoard[1]};
//        } else {
//            return new long[][]{bitBoard[0],{afterLongerLong, afterShorterShort}};
//        }
//    }