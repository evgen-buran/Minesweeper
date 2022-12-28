package com.example.minesweeper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myLog";
    private final int SIZE = 7;
    private final byte BOMBS = 10;
    private GridLayout gridLayout;
    GameField gameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameField = new GameField(SIZE, this);

        setContentView(R.layout.field_layout);
        gridLayout = findViewById(R.id.gridLayout);
        settingViews();

        //create logic array
        gameField.initField();
        //bitmap field from logic array
        paramsCells(gameField);
        gameField.setBomb(BOMBS);
        gameField.setNum();
        gameField.setFieldOnGame();
        setClickers(gameField.getViewCells());

    }


    // description root container(+layoutParams)
    private void settingViews() {
        gridLayout.setColumnCount(SIZE);
        gridLayout.setRowCount(SIZE);
    }
// TODO обработка кликов, получение координат
// TODO открытие пустых, соприкасающихся ячеек (для начала, просто перекрашивать)
// TODO открытие цифровой ячейки
// TODO закрыть ячейки и менять внешний вид по клику


    private void paramsCells(GameField gameField) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(70, 70);

        for (int i = 0; i < gameField.getViewCells().length; i++) {
            for (int j = 0; j < gameField.getViewCells().length; j++) {
                gridLayout.addView(gameField.getViewCells()[i][j], layoutParams);

            }
        }
    }

    public void setClickers(TextView[][] textViews) {
        for (int i = 0; i < textViews.length; i++) {
            for (int j = 0; j < textViews.length; j++) {
                int finalI = i;
                int finalJ = j;
                textViews[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "onClick cell: " + finalI + ":" + finalJ);
                    }
                });
            }
        }
    }
}