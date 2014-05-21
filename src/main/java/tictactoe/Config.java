package tictactoe;

/**
 * All of the configurable dials for the program.
 */
public class Config {

    /* the more games it runs during the training phase, the more data it has to analyze and the better its recommendations will be */
    public static final int NUM_TRAINING_GAMES = 1000000;

    /* the more games it runs during the competition phase, the more obvious the data analysis advantage will be */
    public static final int NUM_COMPETITION_GAMES = 1000000;

    /* whether to print an ASCII representation of each move in both the training and competition phases */
    public static final boolean PRINT_GAMES = false;

    /* whether to print the contents of what it learned after the training phase completes, in mostly human-readable form */
    public static final boolean PRINT_BIG_DATA = false;
}
