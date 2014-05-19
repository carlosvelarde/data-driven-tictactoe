package tictactoe.GameMechanics;

import tictactoe.BigData.BigData;
import tictactoe.BigData.Option;
import tictactoe.BigData.ResultStats;

import java.util.Set;

import static tictactoe.Config.PRINT_GAMES;
import static tictactoe.GameMechanics.Mode.COMPETE;
import static tictactoe.GameMechanics.Mode.LEARN;

public class Controller {

    public void learnTicTacToe(BigData bigData) {
        BoardHistory boardHistory = new BoardHistory();
        BoardStatus boardStatus;
        Player player = Player.getRandomPlayer();

        do {
            boardHistory = takeTurn(player, boardHistory, LEARN, bigData);
            boardStatus = evaluateBoard(boardHistory);
            if (PRINT_GAMES) boardHistory.printCurrentBoard();
            if (boardStatus.isGameOver()) break;
            player = Player.getOtherPlayer(player);
        } while (true);

        Player winner = boardStatus.getWinner();
        if (PRINT_GAMES) System.out.println(winner + " wins\n");
        bigData.addResult(boardHistory, winner);
    }

    public void playTicTacToe(BigData bigData, ResultStats resultStats) {
        BoardHistory boardHistory = new BoardHistory();
        BoardStatus boardStatus;
        Player player = Player.getRandomPlayer();

        do {
            boardHistory = takeTurn(player, boardHistory, Mode.COMPETE, bigData);
            boardStatus = evaluateBoard(boardHistory);
            if (PRINT_GAMES) boardHistory.printCurrentBoard();
            if (boardStatus.isGameOver()) break;
            player = Player.getOtherPlayer(player);
        } while (true);

        Player winner = boardStatus.getWinner();
        resultStats.registerWinner(winner);
        if (PRINT_GAMES) System.out.println(winner + " wins\n");
    }

    private BoardHistory takeTurn(Player player, BoardHistory boardHistory, Mode mode, BigData bigData) {
        Board currentBoard = boardHistory.getCurrentBoard();
        boolean useBigDataToMakeSmartMove = (mode == COMPETE && player == Player.X);
        Position position;
        if (useBigDataToMakeSmartMove) {
            position = askBigDataWhatToDo(bigData, currentBoard);
        } else {
            position = Position.getRandomEmptyPosition(currentBoard);
        }
        Board newBoard = new Board(currentBoard, player, position);
        boardHistory.addBoard(newBoard);
        return boardHistory;
    }

    /**
     * For now, BigData only gives guidance to Player X.
     */
    private Position askBigDataWhatToDo(BigData bigData, Board currentBoard) {
        Set<Option> options = bigData.getOptionsForBoard(currentBoard);
        Position bestNextPosition = null;
        Board bestNextBoard = null;
        float bestGoodness = 0F;

        if (options != null) {
            for (Option option : options) {
                ResultStats resultStats = option.getResultStats();
                float goodnessForThisOption = resultStats.getGoodness(Player.X);
                if (goodnessForThisOption > bestGoodness) {
                    bestNextBoard = option.getBoard();
                }
            }
            if (bestNextBoard != null) {
                bestNextPosition = findDeltaBetweenBoards(currentBoard, bestNextBoard);
            }
        }

        return bestNextPosition;
    }

    private Position findDeltaBetweenBoards(Board earlierBoard, Board laterBoard) {
        Position[] positions = Position.values();
        Position changedPosition = null;
        for (Position position : positions) {
            Player playerOnEarlierBoard = earlierBoard.getPlayerAtPosition(position);
            Player playerOnLaterBoard = laterBoard.getPlayerAtPosition(position);
            if (! playerOnEarlierBoard.equals(playerOnLaterBoard)) {
                changedPosition = position;
                break;
            }
        }
        return changedPosition;
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
