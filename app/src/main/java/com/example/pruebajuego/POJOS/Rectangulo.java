package com.example.pruebajuego.POJOS;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Rectangulo extends Figura{

    private float largo,ancho;

    public Rectangulo(int id, float pos_X, float pos_Y, int color,float largo, float ancho) {
        super(id, pos_X, pos_Y,color);
        this.largo = largo;
        this.ancho = ancho;
    }

    public Rectangulo(int id, float pos_X, float pos_Y, int color,Paint.Style estilo,float largo, float ancho) {
        super(id, pos_X, pos_Y,color,estilo);
        this.largo = largo;
        this.ancho = ancho;
    }

    public float getLargo() {
        return largo;
    }

    public void setLargo(float largo) {
        this.largo = largo;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint p= new Paint();
        p.setAntiAlias(true);
        p.setColor(getColor());
        if(getEstilo()==Paint.Style.STROKE)
            p.setStyle(getEstilo());
        canvas.drawRect(this.getPos_X(),this.getPos_Y(),this.getPos_X()+this.getLargo(),
                this.getPos_Y()+this.getAncho(),p);
    }

    @Override
    public boolean estaDentro(int x, int y) {
        if(x>getPos_X() && x<getPos_X()+getAncho()
                && y>getPos_Y() && y<getPos_Y()+getLargo() && isMover()){
            return true;
        }
        return false;
    }

    @Override
    public boolean figuraHover(Figura figura) {
        if (figura.isMover()==false && figura.getEstilo()!=getEstilo()){
            if(figura instanceof Rectangulo)
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
