package com.example.pruebajuego.POJOSSprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.pruebajuego.GameView;


public class Sprite {

    private int x=0;
    private int xSpeed=5;
    private GameView gameview;
    private Bitmap bmp;

    public Sprite(GameView gameView, Bitmap bmp){
        this.gameview=gameView;
        this.bmp=bmp;
    }

    private void update(){

        x=x+xSpeed;
    }

    public void onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp,x,10,null);
    }

}
