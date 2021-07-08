package com.zybooks.tictactoe;

public class TicTacToeGame {

    private int roundCount;
    private boolean playerXTurn;
    private String winText;
    private boolean gameOver;

    public void newGame() {
        roundCount = 0;
        playerXTurn = true;
        winText = "";
        gameOver = false;
    }

    public void takeTurn(String[][] field) {
        roundCount++;

        if (checkForWin(field)) {
            if (playerXTurn) {
                setWinText("Player X wins!");
            }
            else {
                setWinText("Player O wins!");
            }
            setGameOver(true);
        }
        else if (roundCount == 9) {
            setWinText("It's a draw.");
            setGameOver(true);
        }
        else {
            playerXTurn = !playerXTurn;
        }
    }

    private boolean checkForWin(String[][] field) {
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    public boolean getPlayerXTurn() {
        return playerXTurn;
    }

    public void setPlayerXTurn(boolean playerTurn) {
        playerXTurn = playerTurn;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameStatus) {
        gameOver = gameStatus;
    }

    public String getWinText() {
        return winText;
    }

    public void setWinText(String txt) {
        winText = txt;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int round) {
        roundCount = round;
    }

}
