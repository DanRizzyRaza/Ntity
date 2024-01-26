package com.thg.accelerator23.connectn.ai.hamyal;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thehutgroup.accelerator.connectn.player.Player;

import java.math.BigInteger;
import java.net.SocketTimeoutException;
import java.util.regex.Pattern;

import static com.thg.accelerator23.connectn.ai.hamyal.GameStuff.Analyser.bitBoardShift;
import static com.thg.accelerator23.connectn.ai.hamyal.GameStuff.Analyser.isWin;

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

  public BigInteger[] getCounterPlacements(Board board, Counter counter2) {
    StringBuilder myCurrByte = new StringBuilder();
    StringBuilder opponentCurrByte = new StringBuilder();

    Counter[][] counterPlacements = board.getCounterPlacements();

    for (Counter[] column: counterPlacements) {
      for (Counter counter: column) {
        if (counter == Counter.O) {
          myCurrByte.append("1");
          opponentCurrByte.append("0");
        }
        else if (counter == Counter.X) {
          opponentCurrByte.append("1");
          myCurrByte.append("0");
        } else {
          myCurrByte.append("0");
          opponentCurrByte.append("0");
        }
      }
    }
    // passing byte array into constructor where the byte array is twos complement rep, doesn't matter...
    return new BigInteger[]{new BigInteger(myCurrByte.toString(), 2), new BigInteger(opponentCurrByte.toString(), 2)};
  }

  public long[][] getCounterPlacements(Board board) {
    long move;
    long[][] bitBoard = new long[2][2]; // {home[],away[]}
    int movesPlayed = 0;
//    int count = 0;
    int shift;

    Counter[][] counterPlacements = board.getCounterPlacements();

    for (Counter[] column: counterPlacements) {
      for (Counter counter: column) {
//        count++;
//        if(count % 8 == 0 && count != 0 && count != 64) {
//          movesPlayed++;
//        }
        if(movesPlayed < 90 && (movesPlayed + 1) % 9 == 0) {
          movesPlayed++;
        }

        if (movesPlayed < 64) {
            if (counter == Counter.O) {
              move = 1L << movesPlayed;
              bitBoard[0][0] ^= move;
            } else if (counter == Counter.X) {
              move = 1L << movesPlayed;
              bitBoard[1][0] ^= move;
            }
        } else {
            shift = movesPlayed % 64;
            if (counter == Counter.O) {
              move = 1L << shift;
              bitBoard[0][1] ^= move;
            } else if (counter == Counter.X) {
              move = 1L << shift;
              bitBoard[1][1] ^= move;
            }
        }
        movesPlayed++;
      }
      }
    return bitBoard;
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

    long[] test = getCounterPlacements(board)[0]; //home
    System.out.println(isWin(test));


    return 4;
  }
}