package tictactoe.GameMechanics;

import java.util.Random;

public enum Player {
    X("X"),
    O("O"),
    Neither("_");

    private String displayString;
    /* initialize Random once as a static field to avoid super fast execution
       resulting in the same millisecond value used for several seeds. */
    private static Random rand = new Random(System.currentTimeMillis());

    Player(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return this.displayString;
    }

    public static Player getRandomPlayer() {
        boolean playerIsX = rand.nextBoolean();
        return playerIsX ? Player.X : Player.O;
    }

    public static Player getOtherPlayer(Player player1) {
        if(player1.equals(Player.X)) {
            return Player.O;
        } else {
            return Player.X;
        }
    }
}
