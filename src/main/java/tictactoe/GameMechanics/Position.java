package tictactoe.GameMechanics;

public enum Position {
    NW, N, NE,
    W, MIDDLE, E,
    SW, S, SE;

//    /* initialize Random once as a static field to avoid super fast execution
//       resulting in the same millisecond value used for several seeds. */
//    private static Random rand = new Random(System.currentTimeMillis());
//
//    public static Position getRandomEmptyPosition(Board board) {
//        Position[] allPositions = Position.values();
//        int numPositions = allPositions.length;
//        int randomPositionIndex;
//        Player playerAtRandomPosition;
//        do {
//            randomPositionIndex = rand.nextInt(numPositions);
//            Position randomPosition = allPositions[randomPositionIndex];
//            playerAtRandomPosition = board.getPlayerAtPosition(randomPosition);
//        } while (playerAtRandomPosition != Player.Neither);
//
//        return allPositions[randomPositionIndex];
//    }
}
