package com.thg.accelerator23.connectn.ai.hamyal.minimax;

import com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardRepresentation;
import com.thg.accelerator23.connectn.ai.hamyal.util.MaxSizeHashMap;

import java.util.Arrays;

import static com.thg.accelerator23.connectn.ai.hamyal.Ntity.display;

public class MiniMax {
    BitBoardRepresentation bitBoardRepresentation;
    MaxSizeHashMap<Integer, Integer> transpositionTable = new MaxSizeHashMap<>(2^32); // number of ints? aha

    public MiniMax(BitBoardRepresentation bitBoardRepresentation) {
        this.bitBoardRepresentation = bitBoardRepresentation;
    }


    // remove this??
    public BitBoardRepresentation getBitBoardRepresentation() {
        return bitBoardRepresentation;
    }

    public int hashBoardState(BitBoardRepresentation bitBoardRepresentation){
        // 1st try - hash positions of home players moves in longer long.
        return Long.hashCode(bitBoardRepresentation.getBitBoard()[0][0]);
    }

    // Todo
//
//    public static void main(String[] args) {
//        // Example usage
//        TranspositionTable transpositionTable = new TranspositionTable(1000);
//
//        // Assuming positionHashCode is a unique identifier for a game position
//        int positionHashCode1 = calculateHashCodeForPosition(/* position data */);
//        int evaluationResult1 = evaluatePosition(/* position data */);
//
//        int positionHashCode2 = calculateHashCodeForPosition(/* another position data */);
//        int evaluationResult2 = evaluatePosition(/* another position data */);
//
//        // Store positions in the transposition table
//        transpositionTable.storePosition(positionHashCode1, evaluationResult1);
//        transpositionTable.storePosition(positionHashCode2, evaluationResult2);
//
//        // Look up positions in the transposition table
//        Integer result1 = transpositionTable.lookupPosition(positionHashCode1);
//        Integer result2 = transpositionTable.lookupPosition(positionHashCode2);
//

//    }
//
//    private static int evaluatePosition(/* position data */) {
//        // Implement your own logic to evaluate the position
//        return /* evaluation result */;
//    }
//}

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
        int move = 0;

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

            int thisValue = -NegaMax(bitBoardRepresentation, -beta, -alpha,depth-1, -colour, heuristicMarker);

            if (thisValue > value) {
                value = thisValue;
                move = moveCol;
            }
//            value = Math.max(value, -NegaMax(bitBoardRepresentation, -beta, -alpha,depth-1, -colour, heuristicMarker));
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


//    public static boolean isIntInArray(int target, int[] array) {
//        for (int i : array) {
//            if (i == target) {
//                return true; // The target int is found in the array
//            }
//        }
//        return false; // The target int is not found in the array
//    }
}

// public class TranspositionTable {
//    private final Map<Integer, Integer> table; // Using Integer for simplicity; you may use a custom class for the position

//    public TranspositionTable(int size) {
//        // Create a HashMap with a fixed size
//        this.table = new HashMap<>(size);
//    }
//
//    public void storePosition(int positionHashCode, int evaluationResult) {
//        // Store or override the entry in the transposition table
//        table.put(positionHashCode, evaluationResult);
//    }
//
//    public Integer lookupPosition(int positionHashCode) {
//        // Retrieve the evaluation result for a given position
//        return table.get(positionHashCode);
//    }