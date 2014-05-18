package tictactoe.GameMechanics;

import java.util.ArrayList;
import java.util.List;

public class BoardHistory {
    private List<Board> boards = new ArrayList<Board>();

    /**
     * Ctor: board histories always start with just an empty board.
     */
    public BoardHistory() {
        boards.add(new Board());
    }

    public void addBoard(Board board) {
        boards.add(board);
    }

    public List<Board> getBoards() {
        return boards;
    }

    public int getNumBoards() {
        return boards.size();
    }

    public Board getCurrentBoard() {
        int numBoards = boards.size();
        return boards.get(numBoards - 1);
    }

    public void printCurrentBoard() {
        Board currentBoard = getCurrentBoard();
        System.out.println(currentBoard);
    }
}
