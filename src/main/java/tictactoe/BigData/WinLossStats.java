package tictactoe.BigData;

import tictactoe.Config;
import tictactoe.GameMechanics.Player;

public class WinLossStats {
    private long xWins;
    private long oWins;
    private long ties;

    public WinLossStats() {
        xWins = 0;
        oWins = 0;
        ties = 0;
    }

    public void registerWinner(Player winner) {
        if (winner == Player.X) xWins++;
        else if (winner == Player.O) oWins++;
        else ties++;
    }

    public float getGoodness(Player player) {
        if (player == Player.X) {
            return (float)xWins / (oWins + ties);
        } else if (player == Player.O) {
            return (float)oWins / (xWins + ties);
        } else {
            return -1;
        }
    }

    public String getWinPercentages() {
        float xWinPercentage = calculatePercentageOfTotalGames(xWins);
        float oWinPercentage = calculatePercentageOfTotalGames(oWins);
        float tiePercentage = calculatePercentageOfTotalGames(ties);

        return "X won " + xWins + " games or " + xWinPercentage + "%\n" +
                "O won " + oWins + " games or " + oWinPercentage + "%\n" +
                "there were " + ties + " ties or " + tiePercentage + "%";
    }

    private float calculatePercentageOfTotalGames(long numWins) {
        return (float)numWins / Config.NUM_COMPETITION_GAMES * 100;
    }

    @Override
    public String toString() {
        return xWins + ", " + oWins + ", " + ties;
    }
}
