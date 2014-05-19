package tictactoe.GameMechanics;

import tictactoe.BigData.BigData;
import tictactoe.BigData.ResultStats;

import static tictactoe.Config.*;

public class Main {

    public static void main(String[] args) {
        BigData bigData = new BigData();
        Controller controller = new Controller();

        for (int i = 0; i < NUM_TRAINING_GAMES; i++) {
            controller.learnTicTacToe(bigData);
        }
        if (PRINT_BIG_DATA) System.out.println(bigData);

        ResultStats resultStats = new ResultStats();
        for (int i = 0; i < NUM_COMPETITION_GAMES; i++) {
            controller.playTicTacToe(bigData, resultStats);
        }
        System.out.println(resultStats.generateCompetitionStats());



    }
}