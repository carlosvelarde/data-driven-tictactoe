package tictactoe.GameMechanics;

import java.util.Random;

public enum Player {
    X("X"),
    O("O"),
    Neither("_");

    private String displayString;

    Player(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return this.displayString;
    }

    public static Player getRandomPlayer() {
        Random r = new Random(System.currentTimeMillis());
        boolean playerIsX = r.nextBoolean();
        return playerIsX ? Player.X : Player.O;
    }
}
