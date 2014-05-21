package tictactoe.BigData;

import tictactoe.GameMechanics.Board;
import tictactoe.GameMechanics.BoardHistory;
import tictactoe.GameMechanics.Player;

import java.util.*;

/**
 * Data collection and analysis happens here.
 *
 * The general strategy is to record every game played during the training phase,
 * and keep track of which moves tend to lead to victory. Then during the competition
 * phase, we can ask BigData which move from a particular board setup most often produces
 * a win, and take that move.
 */
public class BigData {

    /* The main data structure for storing all of our data about moves.
       The Board class represents a board configuration, with Xs and Os
       on 0 or more squares. The Option class represents a possible next
       move and success rate associated with making that move. So each Board
       map to a Set of 0 or more Options.

       During the Competition phase, a player can ask BigData to look at all Options
       for a given Board, and return the Option that most often leads to a win.
     */
    private Map<Board, Set<Option>> data;

    public BigData() {
        data = new HashMap<>();
    }

    /**
     * Populate BigData with this method. As each game completes, submit its history of boards and its winner.
     */
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

    public Set<Option> getOptionsForBoard(Board board) {
        return data.get(board);
    }

    @Override
    public String toString() {
        String output = "";
        Set<Board> unsortedBoardsInBigData = data.keySet();
        TreeSet<Board> sortedBoardsInBigData = new TreeSet<>(unsortedBoardsInBigData);

        for (Board boardInBigData : sortedBoardsInBigData) {
            output += "from " + boardInBigData.toString() + "\n";
            Set<Option> optionsForThisBoard = data.get(boardInBigData);
            for (Option option : optionsForThisBoard) {
                output += "  to " + option.toString();
                output += "\n";
            }
        }
        return output;
    }
}
