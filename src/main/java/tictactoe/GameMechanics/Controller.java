package tictactoe.GameMechanics;

import tictactoe.BigData.BigData;

import static tictactoe.Config.PRINT_GAMES;

public class Controller {

    void playTicTacToe(BigData bigData) {
        BoardHistory boardHistory = new BoardHistory();
        BoardStatus boardStatus;
        Player player = Player.getRandomPlayer();

        do {
            boardHistory = takeTurn(player, boardHistory);
            boardStatus = evaluateBoard(boardHistory);
            if (PRINT_GAMES) boardHistory.printCurrentBoard();
            if (boardStatus.isGameOver()) break;
            player = Player.getOtherPlayer(player);
        } while (true);

        Player winner = boardStatus.getWinner();
        if (PRINT_GAMES) System.out.println(winner + " wins\n");
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
        Board currentBoard = boardHistory.getCurrentBoard();
        Player winner = currentBoard.findWinner();

        BoardStatus boardStatus = new BoardStatus();
        boardStatus.setWinner(winner);

        int numBoards = boardHistory.getNumBoards();
        int numPositions = Position.values().length;
        boolean boardIsFull = (numBoards == numPositions + 1);
        boardStatus.setIsFull(boardIsFull);

        return boardStatus;
    }
}
