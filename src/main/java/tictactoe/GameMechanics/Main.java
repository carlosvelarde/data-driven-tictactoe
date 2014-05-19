package tictactoe.GameMechanics;

import tictactoe.BigData.BigData;

import static tictactoe.Config.NUM_TRAINING_GAMES;
import static tictactoe.Config.PRINT_BIG_DATA;

public class Main {

    public static void main(String[] args) {
        BigData bigData = new BigData();
        Controller controller = new Controller();
        for (int i = 0; i < NUM_TRAINING_GAMES; i++) {
            controller.playTicTacToe(bigData);
        }
        if (PRINT_BIG_DATA) System.out.println(bigData);
    }
}