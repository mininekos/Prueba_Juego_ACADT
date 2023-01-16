package com.example.pruebajuego.POJOS;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circulo extends Figura{

    private float radio;

    public Circulo(int id, float por_X, float pos_Y, int color, float radio) {
        super(id, por_X, pos_Y, color);
        this.radio = radio;
    }

    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }


    @Override
    public void onDraw(Canvas canvas) {
        Paint p= new Paint();
        p.setAntiAlias(true);
        p.setColor(getColor());
        canvas.drawCircle(this.getPos_X(),this.getPos_Y(),this.getRadio(),p);
    }
}
