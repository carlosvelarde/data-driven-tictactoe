package tictactoe;

import tictactoe.BigData.BigData;
import tictactoe.BigData.WinLossStats;
import tictactoe.GameMechanics.Controller;

import static tictactoe.Config.*;

public class Main {

    public static void main(String[] args) {
        BigData bigData = new BigData();
        Controller controller = new Controller();

        /* Learning Phase */
        WinLossStats learningStats = new WinLossStats();
        for (int i = 0; i < NUM_TRAINING_GAMES; i++) {
            controller.learnTicTacToe(bigData, learningStats);
        }
        if (PRINT_BIG_DATA) System.out.println(bigData);
        System.out.println("Learning Phase\n" + learningStats.getWinPercentages() + "\n");

        /* Competition Phase */
        WinLossStats competitionStats = new WinLossStats();
        for (int i = 0; i < NUM_COMPETITION_GAMES; i++) {
            controller.playTicTacToe(bigData, competitionStats);
        }
        System.out.println("Competition Phase\n" + competitionStats.getWinPercentages());
    }
}
