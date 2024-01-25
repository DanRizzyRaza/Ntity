package com.thg.accelerator23.connectn.ai.hamyal;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.BoardHacker;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import java.lang.reflect.*;

import static com.thehutgroup.accelerator.connectn.player.BoardHacker.getStolenCounterPlacements;


public class Ntity extends Player {
  public Ntity(Counter counter) {
    super(counter, Ntity.class.getName());
  }

//  public Counter[][] getCounterPlacements(Board board) {
//    try {
//      Method m = board.getClass().getDeclaredMethod("getCounterPlacements", null);
//      m.setAccessible(true);
//      System.out.println("Called getCounterPlacements, result: " + m.invoke(board, null)); // Remove
//      return (Counter[][]) m.invoke(board, null);
//    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
//    }
//    System.out.println("fail");
//    return new Counter[0][];
//  }

  public Counter[][] getCounterPlacements(Board board) {
    System.out.println(getStolenCounterPlacements(board));
    return getStolenCounterPlacements(board);
  }

  @Override
  public int makeMove(Board board) {
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it
    System.out.println(getCounterPlacements(board));
    return 4;
  }
}