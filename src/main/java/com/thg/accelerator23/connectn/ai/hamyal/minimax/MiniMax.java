package com.thg.accelerator23.connectn.ai.hamyal.minimax;

import com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardRepresentation;

public class MiniMax {
    BitBoardRepresentation bitBoardRepresentation;

    MiniMax(BitBoardRepresentation bitBoardRepresentation) {
        this.bitBoardRepresentation = bitBoardRepresentation;
    }

    int NegaMax(BitBoardRepresentation bitBoardRepresentation, int depth, int alpha, int beta) {

        if (depth == 0 || bitBoardRepresentation.isOver() != 0){
            return (bitBoardRepresentation.isOver() == 0b1 ? 1 : -1); // if winner is home then return 1, else return -1 since best second player can hope for is a draw its fine
        }
        var value = -Integer.MAX_VALUE;
        for (int moveCol: bitBoardRepresentation.validMoves()) {
            // Make move, we will undo in a bit
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
