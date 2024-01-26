package com.thg.accelerator23.connectn.ai.hamyal.minimax;

import com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardRepresentation;

public class MiniMax {
    BitBoardRepresentation bitBoardRepresentation;

    public MiniMax(BitBoardRepresentation bitBoardRepresentation) {
        this.bitBoardRepresentation = bitBoardRepresentation;
    }

    public BitBoardRepresentation getBitBoardRepresentation() {
        return bitBoardRepresentation;
    }

    public int NegaMax(BitBoardRepresentation bitBoardRepresentation, int depth, int alpha, int beta) {

        if (depth == 0 || bitBoardRepresentation.isOver() != 0){
            return (bitBoardRepresentation.isOver() == 0b1 ? 1 : -1); // if winner is home then return 1, else return -1 since best second player can hope for is a draw its fine
        }
        var value = -Integer.MAX_VALUE;
        for (int moveCol: bitBoardRepresentation.validMoves()) {
            // Make move, we will undo in a bit
            System.out.println("making a move");

            long[] player0bitboard = bitBoardRepresentation.getBitBoard()[0];
            long[] player1bitboard = bitBoardRepresentation.getBitBoard()[1];
//            System.out.println(String.format("%" + 26 + "s", Long.toBinaryString(player0bitboard[1])).replace(' ', '0') + String.format("%" + 64 + "s", Long.toBinaryString(player0bitboard[0])).replace(' ', '0'));
//            System.out.println(String.format("%" + 26 + "s", Long.toBinaryString(player1bitboard[1])).replace(' ', '0') + String.format("%" + 64 + "s", Long.toBinaryString(player1bitboard[0])).replace(' ', '0'));

            bitBoardRepresentation.makeMove(moveCol);

            int thisValue = -NegaMax(bitBoardRepresentation, depth -1, -beta, -alpha);

            if (thisValue > value) {
                value = thisValue;
            }
            alpha = Math.max(alpha, value);
            if (alpha >= beta) {
                break;
            }

            // Undo the move we made
            bitBoardRepresentation.undoMove();
        }
        return value;
    }
}
