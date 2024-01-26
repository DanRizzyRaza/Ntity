package com.thg.accelerator23.connectn.ai.hamyal;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardAnalyser;
import com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardRepresentation;
import com.thg.accelerator23.connectn.ai.hamyal.minimax.MiniMax;

import java.util.Arrays;

import static com.thg.accelerator23.connectn.ai.hamyal.GameStuff.BitBoardRepresentation.*;

public class Ntity extends Player {
  int[] heightArr;
  int width;
  int height;

  public Ntity(Counter counter, GameConfig gameConfig){
    super(counter, Ntity.class.getName());
    setUpDimensions(gameConfig);
  }
  public Ntity(Counter counter) {
    this(counter, new GameConfig(10,8,4));
  }

  public BitBoardRepresentation getCounterPlacements(Board board) {
    int[] fillLevel = {0,9,18,27,36,45,54,63,72,81};
    int currColumn = 0;
    byte moveCount = 0;

    long move;
    long[][] bitBoard = new long[2][2]; // {home[],away[]}
    int moveLocation = 0;
    int shift;

    Counter[][] counterPlacements = board.getCounterPlacements();

    for (Counter[] column: counterPlacements) {
      for (Counter counter: column) {
//        System.out.println("counter" + counter + " at column" + currColumn);

        if(counter != null) {
          fillLevel[currColumn]++;
          moveCount++;
        }

        if(moveLocation < 90 && (moveLocation + 2) % 9 == 0) {
          currColumn++;
        }

        if(moveLocation < 90 && (moveLocation + 1) % 9 == 0) {
          moveLocation++;
        }

        if (moveLocation < 64) {
            if (counter == Counter.O) {
              move = 1L << moveLocation;
              bitBoard[0][0] ^= move;
            } else if (counter == Counter.X) {
              move = 1L << moveLocation;
              bitBoard[1][0] ^= move;
            }
        } else {
            shift = moveLocation % 64;
            if (counter == Counter.O) {
              move = 1L << shift;
              bitBoard[0][1] ^= move;
            } else if (counter == Counter.X) {
              move = 1L << shift;
              bitBoard[1][1] ^= move;
            }
        }
        moveLocation++;
      }
      }
    return new BitBoardRepresentation(bitBoard, fillLevel, moveCount); // homePlayer is now whichever player we are
  }

  public void setUpDimensions(GameConfig gameConfig) {
    this.width = gameConfig.getWidth();
    this.height = gameConfig.getHeight();
    int[] heightArr = new int[width];
    for(int i=0; i<width; i++) {
      heightArr[i] = i*(height+1);
    }
    this.heightArr = heightArr;
  }

  @Override
  public int makeMove(Board board) {
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it


//    BitBoardRepresentation test = getCounterPlacements(board);
//    System.out.println(Arrays.toString(test.validMoves()));
//    display(test);
//    BitBoardAnalyser trial = new BitBoardAnalyser(test.getBitBoard(), test.getMoveCount(), test.getFillLevel());
//    System.out.println(trial.getIsOver());
//    System.out.println(trial.getWinningMovesCount()[0]);
//    System.out.println(trial.getWinningMovesCount()[1]);

// TODO TESTING HERE
    MiniMax test = new MiniMax(getCounterPlacements(board));
    System.out.println(test.NegaMax(test.getBitBoardRepresentation(),Integer.MIN_VALUE, Integer.MAX_VALUE, 10, 1, 1)[0]);
// TODO TESTING HERE

//    BitBoardRepresentation trial = getCounterPlacements(board);
//    display(trial);
//    System.out.println(Arrays.toString(trial.validMoves()));
//    System.out.println(trial.isOver());


    return 4;
  }

  public static void display(BitBoardRepresentation bb) {
    String bbString = BitBoardToString(bb);
    bbString = reverseString(bbString);
    int curr_start = 8;
    for (int j=0; j<=8;j++) {
      for (int i = 0; i < 10; i++) {
//        System.out.print(curr_start+9*i+ " ");
        System.out.print(bbString.charAt(curr_start + 9 * i));
      }
      System.out.println();
      curr_start--;
    }
  }

  public static String BitBoardToString(BitBoardRepresentation bbrep) {
    long[][] test = bbrep.getBitBoard();

    long longerLong = test[0][0] | test[1][0];
    long shorterLong = test[0][1] | test[1][1];

    return String.format("%" + 26 + "s", Long.toBinaryString(shorterLong)).replace(' ', '0') + String.format("%" + 64 + "s", Long.toBinaryString(longerLong)).replace(' ', '0');
  }

  public static String reverseString(String str) {
    char[] charArray = str.toCharArray();
    int start = 0;
    int end = charArray.length - 1;

    // Swap characters from start to end
    while (start < end) {
      char temp = charArray[start];
      charArray[start] = charArray[end];
      charArray[end] = temp;
      start++;
      end--;
    }

    // Convert the character array back to a string
    return new String(charArray);
  }

}