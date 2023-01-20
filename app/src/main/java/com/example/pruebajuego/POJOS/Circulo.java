package com.example.pruebajuego.POJOS;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Circulo extends Figura{

    private float radio;

    public Circulo(int id, float pos_X, float pos_Y, int color, float radio) {
        super(id, pos_X, pos_Y, color);
        this.radio = radio;
    }

    public Circulo(int id, float pos_X, float pos_Y, int color, Paint.Style estilo,float radio) {
        super(id, pos_X, pos_Y, color,estilo);
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
        if(getEstilo()==Paint.Style.STROKE)
            p.setStyle(getEstilo());
        canvas.drawCircle(this.getPos_X(),this.getPos_Y(),this.getRadio(),p);
    }

    @Override
    public boolean estaDentro(int x, int y) {
        int distanciaX = x-(int) getPos_X();
        int distanciaY = y-(int) getPos_Y();
        if(Math.pow(getRadio(),2)>Math.pow(distanciaX,2)+Math.pow(distanciaY,2) && isMover()){
            return true;
        }
        return false;
    }

    @Override
    public boolean figuraHover(Figura figura) {

        if (figura.isMover()==false && figura.getEstilo()!=getEstilo()){
            if(figura instanceof Circulo)
                return distancia(figura.getPos_X(),figura.getPos_Y(),this.getPos_X(),this.getPos_Y())<=20;
        }
        return false;
    }

    @Override
    public void borrarFigura() {
        setPos_X(100000);
        setPos_Y(100000);
    }
}
