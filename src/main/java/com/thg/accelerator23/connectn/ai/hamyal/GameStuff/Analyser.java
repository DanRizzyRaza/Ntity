package com.thg.accelerator23.connectn.ai.hamyal.GameStuff;

import java.math.BigInteger;
import java.util.ArrayList;

public class Analyser {
    long[][] bitBoard;
    int[] fillLevel; // one above actual location
    int moveCount;
    int[] moves;

    Analyser(long[][] bitBoard, int[] fillLevel, int moveCount, int[] moves) {
        this.bitBoard=bitBoard;
        this.fillLevel=fillLevel;
        this.moveCount=moveCount;
        this.moves=moves;
    }

    void makeMove(int col) {
        long move;
        if (col >= 7 && fillLevel[7] > 64) {
            move = 1L << fillLevel[col]++ - 64;
            bitBoard[moveCount & 1][1] ^= move; // update smaller long
        } else {
            move = 1L << fillLevel[col]++;
            bitBoard[moveCount & 1][0] ^= move;
        }
        moves[moveCount++] = col;
    }

    void undoMove() {
        int col = moves[--moveCount];
        long move;
        if (col >= 7 && fillLevel[7] > 64) {
            move = 1L << --fillLevel[col] - 64;
            bitBoard[moveCount & 1][1] ^= move; // update smaller long
        } else {
            move = 1L << --fillLevel[col];
            bitBoard[moveCount & 1][0] ^= move;
        }
    }

    public static int[] validMoves(int[] fillLevel) {
        ArrayList<Integer> moves = new ArrayList<>();

        long TOPLongerLong = Long.parseUnsignedLong("0100000000100000000100000000100000000100000000100000000100000000",2);
        long TOPShorterLong = 0b10000000010000000010000000; //Long.parseUnsignedLong("10000000010000000010000000",2);

        for(int col = 0; col <= 6; col++) {
            System.out.println(Long.toBinaryString((TOPLongerLong)));
            System.out.println(Long.toBinaryString((1L << fillLevel[col])));
            if ((TOPLongerLong & (1L << fillLevel[col])) == 0) {moves.add(col);}
        }
        for(int col = 8; col <= 9; col++) {
            if ((TOPShorterLong & (1L << (fillLevel[col] - 64))) == 0) {moves.add(col);}
        }
        if(fillLevel[7] < 71) {
            moves.add(7);
        }
        return moves.stream().mapToInt(Integer::intValue).toArray();
    }

    public static boolean isWin(long[] bitBoard) {
        int[] winShiftNumbers = {1,8,9,10}; // gave up on a general solution a while ago
        long[] bbShiftOnce;
        long[] bbShiftTwice;
        long[] bbShiftThrice;

        for(int shift: winShiftNumbers) {
            bbShiftOnce = bitBoardShift(bitBoard, shift);
            bbShiftTwice = bitBoardShift(bbShiftOnce, shift);
            bbShiftThrice = bitBoardShift(bbShiftTwice, shift);

            if (
                    (bitBoard[0] & bbShiftOnce[0] & bbShiftTwice[0] & bbShiftThrice[0]) != 0
                    | (bitBoard[1] & bbShiftOnce[1] & bbShiftTwice[1] & bbShiftThrice[1]) != 0
            ) {
                return true;
            }
        }
        return false;
    }

    public static long[] bitBoardShift(long[] bb, int n) { // never shift by zero, it breaks
        return new long[]{bb[0] << n, (bb[1] << n) | (bb[0] >>> (64 - n))}; // logical right shift
    }


}
