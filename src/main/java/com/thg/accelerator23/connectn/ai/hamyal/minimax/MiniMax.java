package com.thg.accelerator23.connectn.ai.hamyal.minimax;

import com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardRepresentation;

import java.util.Arrays;

import static com.thg.accelerator23.connectn.ai.hamyal.Ntity.display;

public class MiniMax {
    BitBoardRepresentation bitBoardRepresentation;

    public MiniMax(BitBoardRepresentation bitBoardRepresentation) {
        this.bitBoardRepresentation = bitBoardRepresentation;
    }

    public BitBoardRepresentation getBitBoardRepresentation() {
        return bitBoardRepresentation;
    }

    public int NegaMax(BitBoardRepresentation bitBoardRepresentation, int alpha, int beta, int depth, int colour, int heuristicMarker) {
        // for first call color is 1, heuristic marker = 1 is home, -1 if away, doesn't change

        byte gameWinner = bitBoardRepresentation.isOver();

        //============================================
//        System.out.println(gameWinner);
//        System.out.println(depth);
//        System.out.println(bitBoardRepresentation.getMoveCount());
        //============================================

        if (depth == 0 || gameWinner != 0) {
            byte noMoves = bitBoardRepresentation.getMoveCount();
            // heuristic = 41 - no_moves_from_winner
            // biased score for home, multiply by heuristic marker, but also by colour
            if (gameWinner == 1) {
                // odd number of moves
                return colour * heuristicMarker * (40 - (noMoves/2)); //41 -((noMoves/2)+1);
            } else if (gameWinner == 2) {
                return colour * heuristicMarker * ((noMoves/2) - 41);
            } else
//                if (gameWinner == 3)
                {
//                    System.out.println("draw");
                return 0; //draw
            }
        }
        int value = Integer.MIN_VALUE;

        //TODO order child nodes here


//        System.out.println("1: " + Arrays.toString(bitBoardRepresentation.validMoves()));
        for (int moveCol: bitBoardRepresentation.validMoves()) {

            //============================================
//            display(bitBoardRepresentation);
//            System.out.println("2: " + Arrays.toString(bitBoardRepresentation.validMoves()));
//            System.out.println(value);
            //============================================

            // Make move, we will undo in a bit
            bitBoardRepresentation.makeMove(moveCol);

            value = Math.max(value, -NegaMax(bitBoardRepresentation, -beta, -alpha,depth-1, -colour, heuristicMarker));
            alpha = Math.max(alpha, value);

            //Undo the move we made
            bitBoardRepresentation.undoMove();

            if (alpha >= beta) {
                break;
            }

        }
//        bitBoardRepresentation.undoMove();
        return value;
    }


    public static boolean isIntInArray(int target, int[] array) {
        for (int i : array) {
            if (i == target) {
                return true; // The target int is found in the array
            }
        }
        return false; // The target int is not found in the array
    }
//        if (depth == 0 || bitBoardRepresentation.isOver() != 0){
//            return (bitBoardRepresentation.isOver() == 0b1 ? 1 : -1); // if winner is home then return 1, else return -1 since best second player can hope for is a draw its fine
//        }
//        var value = -Integer.MAX_VALUE;
//        System.out.println(Arrays.toString(bitBoardRepresentation.validMoves()));
//        for (int moveCol: bitBoardRepresentation.validMoves()) {
//            // Make move, we will undo in a bit
//            bitBoardRepresentation.makeMove(moveCol);
//
//            int thisValue = -NegaMax(bitBoardRepresentation, depth -1, -beta, -alpha);
//
//            if (thisValue > value) {
//                value = thisValue;
//            }
//            alpha = Math.max(alpha, value);
//            if (alpha >= beta) {
//                break;
//            }
//
//            // Undo the move we made
//            bitBoardRepresentation.undoMove();
//        }
//        return value;
//    }
}
