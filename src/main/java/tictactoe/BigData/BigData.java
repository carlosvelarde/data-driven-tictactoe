package tictactoe.BigData;

import tictactoe.Board;
import tictactoe.BoardHistory;
import tictactoe.Player;

import java.util.*;

public class BigData {

    Map<Board, Set<Option>> data;

    public BigData() {
        data = new HashMap<>();
    }

    public void addResult(BoardHistory boardHistory, Player winner) {
        List<Board> allBoardsInSingleGame = boardHistory.getBoards();
        int numBoardsInSingleGame = allBoardsInSingleGame.size();
        for (Board board : allBoardsInSingleGame) {
            int currentBoardIdx = allBoardsInSingleGame.indexOf(board);
            if (currentBoardIdx < numBoardsInSingleGame - 1) {
                int nextBoardIdx = currentBoardIdx + 1;
                Board boardThatCameNext = allBoardsInSingleGame.get(nextBoardIdx);
                processBoard(board, boardThatCameNext, winner);
            }
        }
    }

    private void processBoard(Board board, Board boardThatCameNext, Player winner) {
        boolean boardAlreadyExists = data.containsKey(board);
        if (boardAlreadyExists) {
            Set<Option> existingOptions = data.get(board);
            updateOptions(existingOptions, boardThatCameNext, winner);
        } else {
            Set<Option> options = new HashSet<Option>();
            updateOptions(options, boardThatCameNext, winner);
            data.put(board, options);
        }
    }

    private void updateOptions(Set<Option> options, Board boardThatCameNext, Player winner) {
        Option optionWithBoardThatCameNext = findOptionWithSpecificBoard(options, boardThatCameNext);
        if (optionWithBoardThatCameNext != null) {
            optionWithBoardThatCameNext.registerWinner(winner);
        } else {
            Option newOption = new Option(boardThatCameNext);
            newOption.registerWinner(winner);
            options.add(newOption);
        }
    }

    private Option findOptionWithSpecificBoard(Set<Option> options, Board board) {
        for (Option option : options) {
            Board boardInOption = option.getBoard();
            if (boardInOption.equals(board)) return option;
        }
        return null;
    }

    @Override
    public String toString() {
        String output = "";
        Set<Board> unsortedBoardsInBigData = data.keySet();
        TreeSet<Board> sortedBoardsInBigData = new TreeSet<Board>(unsortedBoardsInBigData);

        for (Board boardInBigData : sortedBoardsInBigData) {
            output += "Board that we have option data for: " + boardInBigData.toString() + "\n";
            Set<Option> optionsForThisBoard = data.get(boardInBigData);
            for (Option option : optionsForThisBoard) {
                output += "  " + option.toString();
                output += "\n";
            }
        }
        return output;
    }
}
