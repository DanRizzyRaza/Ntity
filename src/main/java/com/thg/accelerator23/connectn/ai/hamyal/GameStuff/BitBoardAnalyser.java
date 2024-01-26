package com.thg.accelerator23.connectn.ai.hamyal.GameStuff;

public class BitBoardAnalyser {
    long[][] bitBoard;
    int moveCount;
//    int[] moves;

    BitBoardAnalyser(BitBoardRepresentation bitBoardRepresentation) {
        this.bitBoard = bitBoardRepresentation.bitBoard;
        this.moveCount = bitBoardRepresentation.moveCount;
//        this.moves = bitBoardRepresentation.moves;
    }

    public int getUtility() {
        return 1;
    };
}
