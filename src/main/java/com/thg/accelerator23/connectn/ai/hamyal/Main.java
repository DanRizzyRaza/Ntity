package com.thg.accelerator23.connectn.ai.hamyal;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;

public class Main {
    public static void main(String[] args) {

        Counter[][] counterArray = new Counter[10][8];


//        counterArray[0][0] = Counter.O;
//
//        counterArray[7][0] = Counter.X;
//        counterArray[7][1] = Counter.O;
//        counterArray[7][2] = Counter.X;
//        counterArray[7][3] = Counter.O;
//        counterArray[7][4] = Counter.X;
//        counterArray[7][5] = Counter.O;
//        counterArray[7][6] = Counter.X;
//        counterArray[7][7] = Counter.O;


        //away wins:
//        counterArray[0][0] = Counter.O;
//        counterArray[0][3] = Counter.X;
//        counterArray[5][0] = Counter.O;
//        counterArray[1][2] = Counter.X;
//        counterArray[6][1] = Counter.O;
//        counterArray[2][1] = Counter.X;
//        counterArray[7][2] = Counter.O;
//        counterArray[3][0] = Counter.X;


        // home wins:
//        counterArray[5][0] = Counter.O;
//        counterArray[0][0] = Counter.X;
//        counterArray[6][1] = Counter.O;
//        counterArray[2][0] = Counter.X;
//        counterArray[7][2] = Counter.O;
//        counterArray[3][0] = Counter.X;
//        counterArray[8][3] = Counter.O;

        // two threes stacks
        counterArray[1][0] = Counter.O;
        counterArray[2][0] = Counter.O;
        counterArray[3][0] = Counter.O;
        counterArray[1][1] = Counter.X;
        counterArray[2][1] = Counter.X;
        counterArray[3][1] = Counter.X;




        //diag
//        counterArray[5][0] = Counter.O;
//        counterArray[6][1] = Counter.O;
//        counterArray[7][2] = Counter.O;
//        counterArray[8][3] = Counter.O;
//
//        //neg-diag
//        counterArray[0][3] = Counter.O;
//        counterArray[1][2] = Counter.O;
//        counterArray[2][1] = Counter.O;
//        counterArray[3][0] = Counter.O;
//
//        //neg-diag 2
//        counterArray[9][0] = Counter.O;
//        counterArray[8][1] = Counter.O;
//        counterArray[7][2] = Counter.O;
//        counterArray[6][3] = Counter.O;
//
//        // else
//        counterArray[4][0] = Counter.O;
//        counterArray[5][3] = Counter.X;
//        counterArray[7][1] = Counter.X;
//        counterArray[9][7] = Counter.X;

        Board testBoard = new Board(counterArray, new GameConfig(10,8,4));

        Ntity testNtity = new Ntity(Counter.O);
//        testNtity.getCounterPlacements(testBoard);
        testNtity.makeMove(testBoard);
    }
}