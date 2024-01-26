package com.thg.accelerator23.connectn.ai.hamyal.GameStuff;

import java.util.ArrayList;

import static com.thg.accelerator23.connectn.ai.hamyal.Ntity.display;

public class BitBoardRepresentation {
    long[][] bitBoard;
    int[] fillLevel; // one above actual location
    byte moveCount;
    int[] moves = new int[80];

    public BitBoardRepresentation(long[][] bitBoard, int[] fillLevel, byte moveCount) {
        this.bitBoard=bitBoard;
        this.fillLevel=fillLevel;
        this.moveCount = moveCount;
    }

    public long[][] getBitBoard() {
        return bitBoard;
    }

    public int[] getFillLevel() {
        return fillLevel;
    }

    public byte getMoveCount() {
        return moveCount;
    }

    public void makeMove(int col) {
//        System.out.println("Player " + (moveCount & 1) + " making a move in column " + col + " now move count is " + (moveCount + 1));
//        display(this);

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

    public void undoMove() {
//        System.out.println("undoing move from column" + moves[moveCount] + " now moves count is " + (moveCount - 1));
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

    public int[] validMoves() {
        ArrayList<Integer> moves = new ArrayList<>();

        long TOPLongerLong = Long.parseUnsignedLong("0100000000100000000100000000100000000100000000100000000100000000",2);
        long TOPShorterLong = 0b10000000010000000010000000; //Long.parseUnsignedLong("10000000010000000010000000",2);

        for(int col = 0; col <= 6; col++) {
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

//    public boolean isOver() {
//        return moves.length == 0
//                || isWin(bitBoard[0])
//                || isWin(bitBoard[1]);
//    };

    public byte isOver() {
        if (isWin(bitBoard[0])) {
            return 0b1;
        } else if (isWin(bitBoard[1])) {
            return 0b10;
        } else if (moves.length == 0) {
            return 0b11;
        }
        return 0b0;
    };

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

//    public BitBoardRepresentation getBitBoardAfterMove(int colToPlay) {
//        BitBoardRepresentation bitBoardAfterMove = new BitBoardRepresentation(bitBoard, fillLevel, moveCount, moves);
//        bitBoardAfterMove.makeMove(colToPlay);
//        return bitBoardAfterMove;
//    }

    public static long[] bitBoardShift(long[] bb, int n) { // never shift by zero, it breaks
        return new long[]{bb[0] << n, (bb[1] << n) | (bb[0] >>> (64 - n))}; // logical right shift
    }

    public static long[] bitBoardShiftAddOnes(long[] bb, int n) { // never shift by zero, it breaks
        return new long[]{((bb[0] << n) | ((1L << n) - 1)), (bb[1] << n) | (bb[0] >>> (64 - n))}; // logical right shift
    }
}

























