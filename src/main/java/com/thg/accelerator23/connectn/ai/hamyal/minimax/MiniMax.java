package com.thg.accelerator23.connectn.ai.hamyal.minimax;

import com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardRepresentation;
import com.thg.accelerator23.connectn.ai.hamyal.util.MaxSizeHashMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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


        for (int moveCol: bitBoardRepresentation.validMoves()) {
//        for (int moveCol: orderMiddleFirst(bitBoardRepresentation.validMoves())) {


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


    public static int[] orderMiddleFirst(int[] listOfNums) {
        Set<Integer> listOfNumsSet = arrayToSet(listOfNums);
        int[] result = new int[listOfNumsSet.size()];
        int counter = 0;
        for(int i: new int[] {4,5,3,6,2,7,1,8,0,9}) {
            if (listOfNumsSet.contains(i)) {
                result[counter++] = i;
            }
        }
        return result;
    }

    private static Set<Integer> arrayToSet(int[] array) {
        Set<Integer> set = new HashSet<>();

        // Iterate through the array and add each element to the set
        for (int value : array) {
            set.add(value);
        }

        return set;
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