package tictactoe.BigData;

import tictactoe.Board;
import tictactoe.Player;

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
        return "option of moving to board " + board.toString() + " has results " + resultStats.toString();
    }
}
