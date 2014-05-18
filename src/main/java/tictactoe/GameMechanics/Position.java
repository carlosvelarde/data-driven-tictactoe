package tictactoe.GameMechanics;

import java.util.Random;

public enum Position {
    NW, N, NE,
    W, MIDDLE, E,
    SW, S, SE;

    public static Position getRandomEmptyPosition(Board board) {
        Position[] allPositions = Position.values();
        int numPositions = allPositions.length;
        Random r = new Random(System.currentTimeMillis());

        int randomPositionIndex;
        Player playerAtRandomPosition;
        do {
            randomPositionIndex = r.nextInt(numPositions);
            Position randomPosition = allPositions[randomPositionIndex];
            playerAtRandomPosition = board.getPlayerAtPosition(randomPosition);
        } while (playerAtRandomPosition != Player.Neither);

        return allPositions[randomPositionIndex];
    }
}
