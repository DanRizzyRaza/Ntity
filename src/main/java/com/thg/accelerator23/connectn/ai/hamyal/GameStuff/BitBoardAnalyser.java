package com.thg.accelerator23.connectn.ai.hamyal.GameStuff;

import static com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardRepresentation.bitBoardShift;
import static com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardRepresentation.bitBoardShiftAddOnes;
import static com.thg.accelerator23.connectn.ai.hamyal.Ntity.reverseString;

public class BitBoardAnalyser {
//    long[][] bitBoard;
//    int moveCount;
////    int[] moves;
//
//    BitBoardAnalyser(BitBoardRepresentation bitBoardRepresentation) {
//        this.bitBoard = bitBoardRepresentation.bitBoard;
//        this.moveCount = bitBoardRepresentation.moveCount;
////        this.moves = bitBoardRepresentation.moves;
//    }
//
//    public int getUtility() {
//        return 1;
//    };

    long[][] bitBoard;
    byte isOver = 0;
    int[] winningMovesCount = new int[2];
    byte moveCount;
    int[] fillLevel;

    public byte getIsOver() {
        return isOver;
    }

    public int[] getWinningMovesCount() {
        return winningMovesCount;
    }

    public BitBoardAnalyser(long[][] bitBoard, byte moveCount, int[] fillLevel) {
        this.bitBoard = bitBoard;
        this.moveCount = moveCount;
        this.fillLevel = fillLevel;

        if (moveCount == 80) {
            isOver = 0b11;
        }

        long[] filledMask = fillLevelToBitBoard(fillLevel); //new long[]{ ~(bitBoard[0][0] | bitBoard[1][0]), ~(bitBoard[0][1] | bitBoard[1][1]) };

        int[] winShiftNumbers = {1,8,9,10};


        for (int player = 0; player <=1; player++) {


            long[] bbShiftOnce;
            long[] bbShiftTwice;
            long[] bbShiftThrice;

            long[] bbAndInverse = new long[]{bitBoard[player][0] ^ filledMask[0], bitBoard[player][1] ^ filledMask[1]};
            long[] bbAndInverseShiftOnce;
            long[] bbAndInverseShiftTwice;
            long[] bbAndInverseShiftThrice;

            for(int shift: winShiftNumbers) {
                bbShiftOnce = bitBoardShift(bitBoard[player], shift);
                bbShiftTwice = bitBoardShift(bbShiftOnce, shift);
                bbShiftThrice = bitBoardShift(bbShiftTwice, shift);

                bbAndInverseShiftOnce = bitBoardShift(bbAndInverse, shift);
                bbAndInverseShiftTwice = bitBoardShift(bbAndInverseShiftOnce, shift);
                bbAndInverseShiftThrice = bitBoardShift(bbAndInverseShiftTwice, shift);
//                inverseMaskShiftThrice = bitBoardShiftAddOnes(inverseMask, 3*shift);

                if (
                        (bitBoard[player][0] & bbShiftOnce[0] & bbShiftTwice[0] & bbShiftThrice[0]) != 0
                                | (bitBoard[player][1] & bbShiftOnce[1] & bbShiftTwice[1] & bbShiftThrice[1]) != 0
                ) {
                    if (player == 0) {
                        isOver = 0b1;
                    } else {
                        isOver = 0b10;
                    }
                }

                int countInLongerLong = countOnes((bbAndInverse[0] & bbAndInverseShiftOnce[0] & bbAndInverseShiftTwice[0] & bbAndInverseShiftThrice[0]));
                int countInShorterShort = countOnes((bbAndInverse[1] & bbAndInverseShiftOnce[1] & bbAndInverseShiftTwice[1] & bbAndInverseShiftThrice[1]));

//                if (
//                        (bitBoard[player][0] & bbShiftOnce[0] & bbShiftTwice[0] & inverseMaskShiftThrice[0]) != 0
//                                | (bitBoard[player][1] & bbShiftOnce[1] & bbShiftTwice[1] & inverseMaskShiftThrice[1]) != 0
//                ) {
//                    winningMovesCount[player]++;
//                }
                winningMovesCount[player]+= countInLongerLong + countInShorterShort;
            }

        }





    }


    public static long[] fillLevelToBitBoard(int[] fillLevel) {
        long longerLong;
        long shorterLong;
        char[] chars = new char[90];
        for(int i = 0; i< 90; i++) {
            chars[i] = '0';
        }
        for (int i = 0; i<10; i++) {
            if ((fillLevel[i] + 1) % 9 != 0) {
                chars[fillLevel[i]] = '1';
            }
        }
        return new long[]{
                Long.parseUnsignedLong(reverseString(new String(chars,0,64)),2),
                Long.parseUnsignedLong(reverseString(new String(chars,64,26)),2)
        };
    }
    public static int countOnes(long number) {
        int count = 0;

        while (number != 0) {
            count += number & 1;
            number = number >>> 1;
        }

        return count;
    }
}
