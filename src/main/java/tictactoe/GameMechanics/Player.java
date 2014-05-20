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

    private Player(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return this.displayString;
    }

    public static Player getRandomPlayer() {
        return rand.nextBoolean() ? Player.X : Player.O;
    }

    public static Player getOtherPlayer(Player player) {
        return player.equals(Player.X) ? Player.O : Player.X;
    }
}
