package com.example.pruebajuego.POJOS;

import android.graphics.Canvas;
import android.graphics.Color;

public abstract class Figura {

    private int id;
    private float pos_X,pos_Y;
    private int color;

    public Figura(int id, float por_X, float pos_Y, int color) {
        this.id = id;
        this.pos_X = por_X;
        this.pos_Y = pos_Y;
        this.color=color;
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

    public abstract void onDraw(Canvas canvas);
}
