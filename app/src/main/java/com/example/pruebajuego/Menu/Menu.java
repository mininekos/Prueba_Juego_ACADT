package com.example.pruebajuego.Menu;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.pruebajuego.POJOS.Figura;

public abstract class Menu {


    private int id;
    private float pos_X,pos_Y;
    private int color;
    private Bitmap bmp;

    public Menu(int id, float pos_X, float pos_Y, int color) {
        this.id = id;
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
        this.color=color;

    }

    public Menu(int id, float por_X, float pos_Y) {
        this.id = id;
        this.pos_X = por_X;
        this.pos_Y = pos_Y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPos_X() {
        return pos_X;
    }

    public void setPos_X(float por_X) {
        this.pos_X = por_X;
    }

    public float getPos_Y() {
        return pos_Y;
    }

    public void setPos_Y(float pos_Y) {
        this.pos_Y = pos_Y;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public abstract void onDraw(Canvas canvas);


}
