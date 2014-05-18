package tictactoe.BigData;

import tictactoe.Player;

public class ResultStats {
    private long xWins;
    private long oWins;
    private long ties;

    public ResultStats() {
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
            return (float)xWins / (float)(oWins + ties);
        } else if (player == Player.O) {
            return (float)oWins / (float)(xWins + ties);
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "xWins: " + xWins + ", oWins: " + oWins + ", ties: " + ties;
    }
}
