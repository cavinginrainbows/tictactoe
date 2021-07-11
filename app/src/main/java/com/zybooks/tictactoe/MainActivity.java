package com.zybooks.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    private final String TAG = "TicTacToe";
    private TicTacToeGame mGame;
    private Button[][] gameGrid;
    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameGrid = new Button[3][3];

        // get references to widgets
        gameGrid[0][0] = (Button) findViewById(R.id.square1);
        gameGrid[0][1] = (Button) findViewById(R.id.square2);
        gameGrid[0][2] = (Button) findViewById(R.id.square3);
        gameGrid[1][0] = (Button) findViewById(R.id.square4);
        gameGrid[1][1] = (Button) findViewById(R.id.square5);
        gameGrid[1][2] = (Button) findViewById(R.id.square6);
        gameGrid[2][0] = (Button) findViewById(R.id.square7);
        gameGrid[2][1] = (Button) findViewById(R.id.square8);
        gameGrid[2][2] = (Button) findViewById(R.id.square9);
        messageTextView = (TextView) findViewById(R.id.messageTextView);

        mGame = new TicTacToeGame();

        startGame();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("gameState", getState());
        outState.putBoolean("gameStatus", mGame.getGameOver());
        outState.putBoolean("playerTurn", mGame.getPlayerXTurn());
        outState.putInt("roundCount", mGame.getRoundCount());
        outState.putString("winText", mGame.getWinText());
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String gameState = savedInstanceState.getString("gameState");
        boolean gameStatus = savedInstanceState.getBoolean("gameStatus");
        boolean playerTurn = savedInstanceState.getBoolean("playerTurn");
        int roundCount = savedInstanceState.getInt("roundCount");
        String winText = savedInstanceState.getString("winText");

        setState(gameState);
        mGame.setGameOver(gameStatus);
        mGame.setPlayerXTurn(playerTurn);
        mGame.setRoundCount(roundCount);
        mGame.setWinText(winText);
        messageTextView.setText(mGame.getWinText());
    }

    public void onBackPressed() {
        moveTaskToBack(false);
    }

    private void startGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameGrid[i][j].setText("");
            }
        }
        mGame.newGame();
        messageTextView.setText(mGame.getWinText());
    }

    public void onNewGameClick(View view) {
        startGame();
    }

    public void onGridButtonClick(View view) {

        if (mGame.getGameOver()) {
            return;
        }
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (mGame.getPlayerXTurn()) {
            ((Button) view).setText("X");
        }
        else {
            ((Button) view).setText("O");
        }

        mGame.takeTurn(setBoard());

        messageTextView.setText(mGame.getWinText());

    }

    public String[][] setBoard() {
        String [][] mBoard = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mBoard[i][j] = gameGrid[i][j].getText().toString();
            }
        }
        return mBoard;
    }

    public String getState() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameGrid[i][j].getText().toString().equals("")) {
                    boardString.append(" ");
                }
                else {
                    boardString.append(gameGrid[i][j].getText().toString());
                }
            }
        }
        return boardString.toString();
    }

    public void setState(String gameState) {
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameState.charAt(index) == ' ') {
                    gameGrid[i][j].setText("");
                }
                else {
                    gameGrid[i][j].setText(Character.toString(gameState.charAt(index)));
                }
                index++;
            }
        }
    }

}