package com.example.minesweeper;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TextView;

import java.util.Random;

public class GameField {
    private final int COLOR_CELLS = R.drawable.shape_cell;

    private int sizeField;
    private Cell[][] cells;
    private TextView[][] viewCells;
    private byte countBombs;
    Context context;

    public TextView[][] getViewCells() {
        return viewCells;
    }

    public void setViewCells(TextView[][] viewCells) {
        this.viewCells = viewCells;
    }

    public GameField(int sizeField, Context context) {
        this.sizeField = sizeField;
        this.cells = new Cell[sizeField][sizeField];
        this.context = context;
        this.viewCells = new TextView[sizeField][sizeField];
    }

    public void initField() {
        for (int i = 0; i < sizeField; i++) {
            for (int j = 0; j < sizeField; j++) {
                cells[i][j] = new Cell();
                viewCells[i][j] = new TextView(context);
                viewCells[i][j].setBackgroundResource(COLOR_CELLS);
                viewCells[i][j].setClickable(true);
                viewCells[i][j].setText(" ");
                viewCells[i][j].setTextSize(22);
                viewCells[i][j].setGravity(Gravity.CENTER);
                viewCells[i][j].setClickable(true);
            }
        }
    }

    public void setBomb(byte countBombs) {
        Random random = new Random();
        byte x, y;
        for (int i = 0; i < countBombs; i++) {
            x = (byte) (random.nextInt(sizeField - 1));
            y = (byte) (random.nextInt(sizeField - 1));
            if (!cells[x][y].isBomb()) cells[x][y].setBomb(true);
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (cells[i][j].isBomb()) {
                    viewCells[i][j].setText("B");
                    viewCells[i][j].setTextSize(22);
                    viewCells[i][j].setTypeface(null, Typeface.BOLD);
                }
            }
        }
    }

    public void setNum() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {

                // угловые ячейки: проверка 3 окружающих точек.
                if (i == 0 && j == 0 && !cells[0][0].isBomb()) {
                    if (cells[i][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j].isBomb()) cells[i][j].setCountContactPlus();
                }

                if (i == 0 && j == cells.length - 1 && !cells[i][j].isBomb()) {
                    if (cells[i][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                }

                if (i == cells.length - 1 && j == 0 && !cells[i][j].isBomb()) {
                    if (cells[i][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j].isBomb()) cells[i][j].setCountContactPlus();
                }

                if (i == cells.length - 1 && j == cells.length - 1 && !cells[i][j].isBomb()) {
                    if (cells[i][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j].isBomb()) cells[i][j].setCountContactPlus();
                }
                // проверка граничных ячеек, кроме угловых: проверка пяти окружающих ячеек
                //верхняя строка
                if (i == 0 && (j > 0 && j < cells.length - 1) && !cells[i][j].isBomb()) {
                    if (cells[i][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                }
                //нижняя строка
                if (i == cells.length - 1 && (j > 0 && j < cells.length - 1) && !cells[i][j].isBomb()) {
                    if (cells[i][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    cells[i][j].setCountContactPlus();
                }
                //левый столбец
                if ((i > 0 && i < cells.length - 1) && j == 0 && !cells[i][j].isBomb()) {
                    if (cells[i - 1][j].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j].isBomb()) cells[i][j].setCountContactPlus();
                }
                //правый столбец
                if ((i > 0 && i < cells.length - 1) && j == cells.length - 1 && !cells[i][j].isBomb()) {
                    if (cells[i - 1][j].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j].isBomb()) cells[i][j].setCountContactPlus();
                }

                //остальные ячейки: проверяются 8 ячеек
                if ((i >= 1 && i <= cells.length - 2) && (j >= 1 && j <= cells.length - 2) && !cells[i][j].isBomb()) {
                    if (cells[i - 1][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i - 1][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j + 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i + 1][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                    if (cells[i][j - 1].isBomb()) cells[i][j].setCountContactPlus();
                }
            }
        }
    }

    //color of cell dependent on state
    public void setFieldOnGame() {
        for (int i = 0; i < sizeField; i++) {
            for (int j = 0; j < sizeField; j++) {
                if (cells[i][j].getCountContact() > 0) {
                    viewCells[i][j].setText(cells[i][j].getCountContact() + "");
                    viewCells[i][j].setTextSize(22);
                }
            }
        }

    }


    public int getSizeField() {
        return sizeField;
    }

    public void setSizeField(int sizeField) {
        this.sizeField = sizeField;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
