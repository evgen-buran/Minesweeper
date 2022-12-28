package com.example.minesweeper;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

public class Cell {
    private boolean isBomb;
    private boolean isContact;
    private boolean isClicked;
    private int drawableCell;
    private int countContact;

    public int getDrawableCell() {
        return drawableCell;
    }

    public void setDrawableCell(int drawableCell) {
        this.drawableCell = drawableCell;
    }

    public int getCountContact() {
        return countContact;
    }

    public void setCountContactPlus() {
        this.countContact++;
    }

    public Cell() {
        this.isClicked = false;
        this.countBomb = 0;
        this.countContact = 0;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    private byte countBomb;

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isContact() {
        return isContact;
    }

    public void setContact(boolean contact) {
        isContact = contact;
    }

    public byte getCountBomb() {
        return countBomb;
    }

    public void setCountBomb(byte countBomb) {
        this.countBomb = countBomb;
    }

    public void setParamsCell() {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(60, 60);

    }
}
