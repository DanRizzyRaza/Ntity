package com.thg.accelerator23.connectn.ai.hamyal;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;

public class Main {
    public static void main(String[] args) {

        Counter[][] counterArray = new Counter[10][8];
        //diag
        counterArray[5][0] = Counter.O;
        counterArray[6][1] = Counter.O;
        counterArray[7][2] = Counter.O;
        counterArray[8][3] = Counter.O;

        //neg-diag
        counterArray[0][3] = Counter.O;
        counterArray[1][2] = Counter.O;
        counterArray[2][1] = Counter.O;
        counterArray[3][0] = Counter.O;

        //neg-diag 2
        counterArray[9][0] = Counter.O;
        counterArray[8][1] = Counter.O;
        counterArray[7][2] = Counter.O;
        counterArray[6][3] = Counter.O;

        // else
        counterArray[4][0] = Counter.O;
        counterArray[5][3] = Counter.X;
        counterArray[7][1] = Counter.X;
        counterArray[9][7] = Counter.X;

        Board testBoard = new Board(counterArray, new GameConfig(10,8,4));

        Ntity testNtity = new Ntity(Counter.O);
//        testNtity.getCounterPlacements(testBoard);
        testNtity.makeMove(testBoard);
    }
}