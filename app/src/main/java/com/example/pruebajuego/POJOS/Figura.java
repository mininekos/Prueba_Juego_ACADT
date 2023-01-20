package com.example.pruebajuego.POJOS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Figura {

    private int id;
    private float pos_X,pos_Y;
    private int color;
    private Bitmap bmp;
    private Paint.Style estilo;
    private boolean mover;

    public Figura(int id, float pos_X, float pos_Y, int color) {
        this.id = id;
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
        this.color=color;
        mover=true;
    }

    public Figura(int id, float pos_X, float pos_Y, int color,Paint.Style estilo) {
        this.id = id;
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
        this.color=color;
        this.estilo=estilo;
        mover=false;
    }

    public Figura(int id, float por_X, float pos_Y) {
        this.id = id;
        this.pos_X = por_X;
        this.pos_Y = pos_Y;
        mover=true;
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

    public Paint.Style getEstilo() {
        return estilo;
    }

    public void setEstilo(Paint.Style estilo) {
        this.estilo = estilo;
    }

    public boolean isMover() {
        return mover;
    }

    public void setMover(boolean mover) {
        this.mover = mover;
    }

    public abstract void onDraw(Canvas canvas);

    public abstract boolean estaDentro(int x,int y);

    public abstract boolean figuraHover(Figura figura);

    public double distancia(float x1, float y1, float x2, float y2){
        return  Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
    }

    public abstract void borrarFigura();

    public void updatePosition(int x, int y)
    {
        setPos_X(getPos_X()+x);
        setPos_Y(getPos_Y()+y);
    }
}
