package tictactoe.GameMechanics;

class BoardStatus {

    private Player winner;
    private boolean boardIsFull;

    public BoardStatus() {
        winner = Player.Neither;
        boardIsFull = false;
    }

    public boolean isGameOver() {
        return (boardIsFull ||
                getWinner() == Player.X ||
                getWinner() == Player.O);
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player newWinner) {
        winner = newWinner;
    }

    public void setIsFull(boolean newBoardIsFull) {
        boardIsFull = newBoardIsFull;
    }
}
