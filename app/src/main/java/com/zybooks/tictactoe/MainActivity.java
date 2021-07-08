package com.zybooks.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

        if (mGame.isGameOver()) {
            return;
        }
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (mGame.isPlayerXTurn()) {
            ((Button) view).setText("X");
        }
        else {
            ((Button) view).setText("O");
        }

        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = gameGrid[i][j].getText().toString();
            }
        }
        mGame.takeTurn(field);

        if (mGame.isGameOver()) {
            messageTextView.setText(mGame.getWinText());
        }
    }
}