package tictactoe;

public class BoardStatus {
    Player winner;
    boolean boardIsFull;

    public BoardStatus() {
        winner = Player.Neither;
        boardIsFull = false;
    }

    public boolean isBoardFull() {
        return boardIsFull;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return (isBoardFull() ||
                getWinner() == Player.X ||
                getWinner() == Player.O);
    }

    public void setWinner(Player newWinner) {
        winner = newWinner;
    }

    public void setIsFull(boolean newBoardIsFull) {
        boardIsFull = newBoardIsFull;
    }
}
