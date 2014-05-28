package tictactoe.BigData;

import org.testng.annotations.Test;
import tictactoe.GameMechanics.Board;
import tictactoe.GameMechanics.Player;

import static org.testng.Assert.*;

public class TestOption {
    @Test
    public void TestConstructor() {

        /* exercise the constructor */
        Board emptyBoard = new Board();
        Option option = new Option(emptyBoard);

        /* getWinLossStats() should return a WinLossStats object */
        assertNotNull(option.getWinLossStats());

        /* getBoard() should return the empty board we passed in to the constructor */
        assertEquals(option.getBoard(), emptyBoard);

        /* registering a winner with register() should change the toString() value of this Option */
        String optionAsStringBeforeRegisterWinner = option.toString();
        option.registerWinner(Player.O);
        String optionAsStringAfterRegisterWinner = option.toString();
        assertNotSame(optionAsStringBeforeRegisterWinner, optionAsStringAfterRegisterWinner);
    }
}
