package tictactoe.GameMechanics;

import java.util.EnumMap;
import java.util.Map;

public class Board implements Comparable{

    public static final String COLUMN_SEPARATOR = " ";
    public static final String PRETTY_COLUMN_SEPARATOR = " | ";
    public static final String PRETTY_ROW_SEPARATOR = "\n";

    Map<Position, Player> squares = new EnumMap<Position, Player>(Position.class); // where each x and o lives

    /**
     * Ctor for first board of game
     */
    public Board() {
        squares.put(Position.NW, Player.Neither);
        squares.put(Position.N, Player.Neither);
        squares.put(Position.NE, Player.Neither);
        squares.put(Position.W, Player.Neither);
        squares.put(Position.MIDDLE, Player.Neither);
        squares.put(Position.E, Player.Neither);
        squares.put(Position.SW, Player.Neither);
        squares.put(Position.S, Player.Neither);
        squares.put(Position.SE, Player.Neither);
    }

    /**
     * Ctor for all boards other than first board of the game
     */
    public Board(Board boardBeforeMove, Player player, Position position) {
        squares.putAll(boardBeforeMove.squares); // copy old board to new board
        squares.put(position, player); // make the new move
    }

    @Override
    public String toString() {
        Player nwPlayer = squares.get(Position.NW);
        Player nPlayer = squares.get(Position.N);
        Player nePlayer = squares.get(Position.NE);
        Player wPlayer = squares.get(Position.W);
        Player middlePlayer = squares.get(Position.MIDDLE);
        Player ePlayer = squares.get(Position.E);
        Player swPlayer = squares.get(Position.SW);
        Player sPlayer = squares.get(Position.S);
        Player sePlayer = squares.get(Position.SE);

        return nwPlayer.getDisplayString() +
                nPlayer.getDisplayString() +
                nePlayer.getDisplayString() + COLUMN_SEPARATOR +
                wPlayer.getDisplayString() +
                middlePlayer.getDisplayString() +
                ePlayer.getDisplayString() + COLUMN_SEPARATOR +
                swPlayer.getDisplayString() +
                sPlayer.getDisplayString() +
                sePlayer.getDisplayString();
    }

    public String toPrettyString() {
        Player nwPlayer = squares.get(Position.NW);
        Player nPlayer = squares.get(Position.N);
        Player nePlayer = squares.get(Position.NE);
        Player wPlayer = squares.get(Position.W);
        Player middlePlayer = squares.get(Position.MIDDLE);
        Player ePlayer = squares.get(Position.E);
        Player swPlayer = squares.get(Position.SW);
        Player sPlayer = squares.get(Position.S);
        Player sePlayer = squares.get(Position.SE);

        return nwPlayer.getDisplayString() + PRETTY_COLUMN_SEPARATOR +
                nPlayer.getDisplayString() + PRETTY_COLUMN_SEPARATOR +
                nePlayer.getDisplayString() + PRETTY_ROW_SEPARATOR +
                wPlayer.getDisplayString() + PRETTY_COLUMN_SEPARATOR +
                middlePlayer.getDisplayString() + PRETTY_COLUMN_SEPARATOR +
                ePlayer.getDisplayString() + PRETTY_ROW_SEPARATOR +
                swPlayer.getDisplayString() + PRETTY_COLUMN_SEPARATOR +
                sPlayer.getDisplayString() + PRETTY_COLUMN_SEPARATOR +
                sePlayer.getDisplayString();
    }

    public Player getPlayerAtPosition(Position position) {
        return squares.get(position);
    }

    public Player findWinner() {
        Player winner = checkSquareTripleForWinner(Position.NW, Position.N, Position.NE); // row 1
        if (winner == Player.Neither)
            winner = checkSquareTripleForWinner(Position.W, Position.MIDDLE, Position.E); // row 2
        if (winner == Player.Neither)
            winner = checkSquareTripleForWinner(Position.SW, Position.S, Position.SE); // row 2
        if (winner == Player.Neither)
            winner = checkSquareTripleForWinner(Position.NW, Position.W, Position.SW); // col 1
        if (winner == Player.Neither)
            winner = checkSquareTripleForWinner(Position.N, Position.MIDDLE, Position.S); // col 2
        if (winner == Player.Neither)
            winner = checkSquareTripleForWinner(Position.NE, Position.E, Position.SE); // col 3
        if (winner == Player.Neither)
            winner = checkSquareTripleForWinner(Position.NW, Position.MIDDLE, Position.SE); // diag 1
        if (winner == Player.Neither)
            winner = checkSquareTripleForWinner(Position.NE, Position.MIDDLE, Position.SW); // diag 2
        return winner;
    }

    private Player checkSquareTripleForWinner(
            Position position1, Position position2, Position position3) {
        Player player1 = squares.get(position1);
        Player player2 = squares.get(position2);
        Player player3 = squares.get(position3);
        boolean playersMatch = (player1 == player2 && player2 == player3);
        return playersMatch ? player1 : Player.Neither;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public int compareTo(Object o) {
        if (! (o instanceof Board)) return 0;
        String thisAsString = this.toString();
        String otherBoardAsString = ((Board)o).toString();
//        return (otherBoardAsString.compareTo(thisAsString)); // most common boards on top
        return (thisAsString.compareTo(otherBoardAsString)); // most common boards on bottom
    }
}
