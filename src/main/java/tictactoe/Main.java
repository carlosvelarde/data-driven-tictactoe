package tictactoe;

import tictactoe.BigData.BigData;

public class Main {
    public static void main(String[] args) {
        BigData bigData = new BigData();
        for (int i = 0; i < 1000; i++) {
            new Main().playTicTacToe(bigData);
        }
        System.out.println(bigData);
    }

    private void playTicTacToe(BigData bigData) {
        BoardHistory boardHistory = new BoardHistory();
        BoardStatus boardStatus;

        do {
            boardHistory = takeTurn(Player.X, boardHistory);
            boardStatus = evaluateBoard(boardHistory);
            if (Config.PRINT_BOARDS) boardHistory.printCurrentBoard();
            if (boardStatus.isGameOver()) break;

            boardHistory = takeTurn(Player.O, boardHistory);
            boardStatus = evaluateBoard(boardHistory);
            if (Config.PRINT_BOARDS) boardHistory.printCurrentBoard();
            if (boardStatus.isGameOver()) break;
        } while (true);

        Player winner = boardStatus.getWinner();
//        System.out.println("winner: " + winner);
        bigData.addResult(boardHistory, winner);
    }

    private BoardHistory takeTurn(Player player, BoardHistory boardHistory) {
        Board currentBoard = boardHistory.getCurrentBoard();
        Position randomEmptyPosition = Position.getRandomEmptyPosition(currentBoard);
        Board newBoard = new Board(currentBoard, player, randomEmptyPosition);
        boardHistory.addBoard(newBoard);
        return boardHistory;
    }

    private BoardStatus evaluateBoard(BoardHistory boardHistory) {
        BoardStatus boardStatus = new BoardStatus();
        Board currentBoard = boardHistory.getCurrentBoard();

        Player winner = currentBoard.findWinner();
        boardStatus.setWinner(winner);

        int numBoards = boardHistory.getNumBoards();
        int numPositions = Position.values().length;
        boolean boardIsFull = (numBoards == numPositions + 1);
        boardStatus.setIsFull(boardIsFull);

        return boardStatus;
    }

    private void addToBigData(BoardHistory boardHistory, Player winner) {

    }
}