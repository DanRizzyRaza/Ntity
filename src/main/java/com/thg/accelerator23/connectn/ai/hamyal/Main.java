package com.thg.accelerator23.connectn.ai.hamyal;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;

public class Main {
    public static void main(String[] args) {

        Counter[][] counterArray = new Counter[10][8];
        counterArray[0][0] = Counter.O;
        counterArray[1][0] = Counter.X;
        counterArray[2][0] = Counter.O;
        counterArray[5][3] = Counter.X;
        counterArray[7][1] = Counter.X;
        counterArray[9][7] = Counter.X;

        Board testBoard = new Board(counterArray, new GameConfig(10,8,4));

        Ntity testNtity = new Ntity(Counter.O);
//        testNtity.getCounterPlacements(testBoard);
        testNtity.makeMove(testBoard);
    }
}