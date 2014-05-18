package tictactoe.BigData;

import tictactoe.GameMechanics.Board;
import tictactoe.GameMechanics.Player;

public class Option {
    private Board board;
    private ResultStats resultStats;

    public Option(Board board) {
        this.board = board;
        this.resultStats = new ResultStats();
    }

    public Board getBoard() {
        return board;
    }

    public ResultStats getResultStats() {
        return resultStats;
    }

    public void registerWinner(Player winner) {
        resultStats.registerWinner(winner);
    }

    @Override
    public String toString() {
        return board.toString() + " produces " + resultStats.toString();
    }
}
