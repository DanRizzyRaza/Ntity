package com.thg.accelerator23.connectn.ai.hamyal;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;

public class Main {
    public static void main(String[] args) {

        Counter[][] counterArray = new Counter[10][10];
        counterArray[0][0] = Counter.O;
        counterArray[1][1] = Counter.X;

        Board testBoard = new Board(counterArray, new GameConfig(10,8,4));

        Ntity testNtity = new Ntity(Counter.O);
        testNtity.getCounterPlacements(testBoard);
    }
}
