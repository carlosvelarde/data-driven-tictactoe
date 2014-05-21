package tictactoe.GameMechanics;

import tictactoe.BigData.BigData;
import tictactoe.BigData.Option;
import tictactoe.BigData.WinLossStats;

import java.util.Set;

import static tictactoe.Config.PRINT_GAMES;
import static tictactoe.GameMechanics.Phase.COMPETE;
import static tictactoe.GameMechanics.Phase.LEARN;

public class Controller {

    public void learnTicTacToe(BigData bigData, WinLossStats winLossStats) {
        BoardHistory boardHistory = new BoardHistory();
        BoardStatus boardStatus;
        Player player = Player.getRandomPlayer();

        do {
            boardHistory = takeTurn(player, boardHistory, LEARN, bigData);
            boardStatus = evaluateBoard(boardHistory);
            if (PRINT_GAMES) boardHistory.printLatestBoard();
            if (boardStatus.isGameOver()) break;
            player = Player.getOtherPlayer(player);
        } while (true);

        Player winner = boardStatus.getWinner();
        winLossStats.registerWinner(winner);
        if (PRINT_GAMES) System.out.println(winner + " wins\n");
        bigData.addResult(boardHistory, winner);
    }

    private BoardHistory takeTurn(Player player, BoardHistory boardHistory, Phase phase, BigData bigData) {
        Board currentBoard = boardHistory.getLatestBoard();
        boolean useBigDataToMakeSmartMove = (phase == COMPETE && player == Player.X);
        Position position;
        if (useBigDataToMakeSmartMove) {
            position = getAdviceFromBigData(bigData, currentBoard, player);
        } else {
            position = currentBoard.getRandomEmptyPosition();
        }
        Board newBoard = new Board(currentBoard, player, position);
        boardHistory.addBoard(newBoard);
        return boardHistory;
    }

    private BoardStatus evaluateBoard(BoardHistory boardHistory) {
        Board currentBoard = boardHistory.getLatestBoard();
        Player winner = currentBoard.findWinner();

        BoardStatus boardStatus = new BoardStatus();
        boardStatus.setWinner(winner);

        int numBoards = boardHistory.getNumBoards();
        int numPositions = Position.values().length;
        boolean boardIsFull = (numBoards == numPositions + 1);
        boardStatus.setIsFull(boardIsFull);

        return boardStatus;
    }

    private Position getAdviceFromBigData(BigData bigData, Board currentBoard, Player player) {
        Set<Option> options = bigData.getOptionsForBoard(currentBoard);
        Position bestNextPosition = null;
        Board bestNextBoard = null;
        float bestGoodness = 0F;

        if (options != null) {
            for (Option option : options) {
                WinLossStats winLossStats = option.getWinLossStats();
                float goodnessForThisOption = winLossStats.getGoodness(Player.X);
                if (goodnessForThisOption > bestGoodness) {
                    bestNextBoard = option.getBoard();
                    Position deltaPosition = findDeltaBetweenBoards(currentBoard, bestNextBoard);
                    Player playerWhoMoved = bestNextBoard.getPlayerAtPosition(deltaPosition);
                    if (playerWhoMoved.equals(player)) {
                        bestNextPosition = deltaPosition;
                        bestGoodness = goodnessForThisOption;
                    }
                }
            }
        }

        if (bestNextPosition == null) {
            bestNextPosition = currentBoard.getRandomEmptyPosition();
        }

        return bestNextPosition;
    }

    private Position findDeltaBetweenBoards(Board earlierBoard, Board laterBoard) {
        Position[] positions = Position.values();
        Position changedPosition = null;
        for (Position position : positions) {
            Player playerOnEarlierBoard = earlierBoard.getPlayerAtPosition(position);
            Player playerOnLaterBoard = laterBoard.getPlayerAtPosition(position);
            if (!playerOnEarlierBoard.equals(playerOnLaterBoard)) {
                changedPosition = position;
                break;
            }
        }
        return changedPosition;
    }

    public void playTicTacToe(BigData bigData, WinLossStats winLossStats) {
        BoardHistory boardHistory = new BoardHistory();
        BoardStatus boardStatus;
        Player player = Player.getRandomPlayer();

        do {
            boardHistory = takeTurn(player, boardHistory, Phase.COMPETE, bigData);
            boardStatus = evaluateBoard(boardHistory);
            if (PRINT_GAMES) boardHistory.printLatestBoard();
            if (boardStatus.isGameOver()) break;
            player = Player.getOtherPlayer(player);
        } while (true);

        Player winner = boardStatus.getWinner();
        winLossStats.registerWinner(winner);
        if (PRINT_GAMES) System.out.println(winner + " wins\n");
    }
}
