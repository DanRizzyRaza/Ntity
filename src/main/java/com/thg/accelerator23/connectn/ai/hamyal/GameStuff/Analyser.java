package com.thg.accelerator23.connectn.ai.hamyal.GameStuff;

import java.math.BigInteger;

public class Analyser {
    BigInteger[] bitBoard;
    int[] fillLevel;
    int moveCount;
    int[] moves;

    Analyser(BigInteger[] bitBoard, int[] fillLevel, int moveCount, int[] moves) {
        this.bitBoard=bitBoard;
        this.fillLevel=fillLevel;
        this.moveCount=moveCount;
        this.moves=moves;
    }

    void makeMove(int col) {
        BigInteger move = (new BigInteger("1",2)).shiftLeft(fillLevel[col]++);
        bitBoard[moveCount & 1] = bitBoard[moveCount & 1].xor(move);
        moves[moveCount++] = col;
    }

    void undoMove() {
        int col = moves[--moveCount];
        BigInteger move = (new BigInteger("1",2)).shiftLeft(--fillLevel[col]);
        bitBoard[moveCount & 1] = bitBoard[moveCount & 1].xor(move);
    }


}
