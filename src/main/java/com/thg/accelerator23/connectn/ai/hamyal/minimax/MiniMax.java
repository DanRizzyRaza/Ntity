package com.thg.accelerator23.connectn.ai.hamyal.minimax;

import com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardRepresentation;
import com.thg.accelerator23.connectn.ai.hamyal.util.MaxSizeHashMap;

import java.util.Arrays;

import static com.thg.accelerator23.connectn.ai.hamyal.Ntity.display;

public class MiniMax {
    BitBoardRepresentation bitBoardRepresentation;
    // Transposition table [depth, flag, value, move] flag is 0 for EXACT, 1 for LOWERBOUND, 2 for UPPERBOUND
    MaxSizeHashMap<Integer, Integer[]> transpositionTable = new MaxSizeHashMap<>(2^32); // number of ints? aha

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

    public int[] NegaMax(BitBoardRepresentation bitBoardRepresentation, int alpha, int beta, int depth, int colour, int heuristicMarker) {
        // output is [value, move]
        // for first call color is 1, heuristic marker = 1 is home, -1 if away, doesn't change
        int alphaStart = alpha;
        // Transposition table [depth, flag, value, move] flag is 0 for EXACT, 1 for LOWERBOUND, 2 for UPPERBOUND
        Integer[] ttEntry = transpositionTable.get(hashBoardState(bitBoardRepresentation));
        if (ttEntry != null && ttEntry[0] >= depth) {
            if (ttEntry[1] == 0) {
                return new int[]{ttEntry[2], ttEntry[3]};
            } else if (ttEntry[1] == 1) {
                alpha = Math.max(alpha, ttEntry[2]);
            } else if (ttEntry[1] == 2) {
                beta = Math.min(beta, ttEntry[2]);
            }
            if (alpha >= beta) {
                return new int[]{ttEntry[2], ttEntry[3]};
            }
        } else {
            ttEntry = new Integer[4];
        }

        //============================================
//        System.out.println(gameWinner);
//        System.out.println(depth);
//        System.out.println(bitBoardRepresentation.getMoveCount());
        //============================================

        byte gameWinner = bitBoardRepresentation.isOver();

        if (depth == 0 || gameWinner != 0) {
            byte noMoves = bitBoardRepresentation.getMoveCount();
            // heuristic = 41 - no_moves_from_winner
            // biased score for home, multiply by heuristic marker, but also by colour
            if (gameWinner == 1) {
                // odd number of moves
                return new int[]{colour * heuristicMarker * (40 - (noMoves/2)), 0}; //41 -((noMoves/2)+1);
            } else if (gameWinner == 2) {
                return new int[]{colour * heuristicMarker * ((noMoves/2) - 41), 0};
            } else
//                if (gameWinner == 3)
                {
//                    System.out.println("draw");
                return new int[]{0,0}; //draw
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

            int thisValue = -NegaMax(bitBoardRepresentation, -beta, -alpha,depth-1, -colour, heuristicMarker)[1];

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

        // Storing to Transposition table [depth, flag, value, move] flag is 0 for EXACT, 1 for LOWERBOUND, 2 for UPPERBOUND
        ttEntry[2] = value;
        if (value <= alphaStart) {
            ttEntry[1] = 2;
        } else if (value >= beta) {
            ttEntry[1] = 1;
        } else {
            ttEntry[1] = 0;
        }
        ttEntry[0] = depth;
        ttEntry[3] = move;


        return new int[]{value, move};
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