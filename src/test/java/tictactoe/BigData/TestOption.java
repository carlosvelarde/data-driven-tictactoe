package tictactoe.BigData;

import org.testng.annotations.Test;
import tictactoe.GameMechanics.Board;
import tictactoe.GameMechanics.Player;

import static org.testng.Assert.*;

public class TestOption {
    @Test
    public void TestConstructor() {
        Board emptyBoard = new Board();
        Option option = new Option(emptyBoard);

        WinLossStats winLossStats1InOption = option.getWinLossStats();
        assertNotNull(winLossStats1InOption);

        Board boardInOption = option.getBoard();
        assertEquals(boardInOption, emptyBoard);

        option.registerWinner(Player.O);
        WinLossStats winLossStats2InOption = option.getWinLossStats();
        float goodnessBeforeRegisteringWin = winLossStats1InOption.getGoodness(Player.O);
        float goodnessAfterRegisteringWin = winLossStats2InOption.getGoodness(Player.O);
        assertTrue(goodnessAfterRegisteringWin > goodnessBeforeRegisteringWin);
    }
}
