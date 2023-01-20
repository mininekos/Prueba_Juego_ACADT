package com.example.pruebajuego.POJOS;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.pruebajuego.R;

public class Imagen extends Figura{
    private Bitmap bmp;
    private Bitmap img;
    private int largo,ancho;

    public Imagen(int id, float pos_X, float pos_Y, Resources resources, int codImg, int largo, int ancho) {
        super(id, pos_X, pos_Y);
        this.largo=largo;
        this.ancho=ancho;
        bmp= BitmapFactory.decodeResource(resources, codImg);
        img=bmp.createScaledBitmap(bmp,largo ,ancho,true);
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(img,getPos_X(),getPos_Y(),null);
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
        return false;
    }

    @Override
    public void borrarFigura() {
        setPos_X(100000);
        setPos_Y(100000);
    }

}
