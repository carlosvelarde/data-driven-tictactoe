package tictactoe.BigData;

import tictactoe.GameMechanics.Board;
import tictactoe.GameMechanics.Player;

public class Option {
    private Board board;
    private WinLossStats winLossStats;

    public Option(Board board) {
        this.board = board;
        this.winLossStats = new WinLossStats();
    }

    public Board getBoard() {
        return board;
    }

    public WinLossStats getWinLossStats() {
        return winLossStats;
    }

    public void registerWinner(Player winner) {
        winLossStats.registerWinner(winner);
    }

    @Override
    public String toString() {
        return board.toString() + " results in " + winLossStats.toString();
    }
}
