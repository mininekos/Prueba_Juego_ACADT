package com.example.pruebajuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.pruebajuego.Hilos.GameLoopThread;
import com.example.pruebajuego.POJOSSprite.Sprite;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private Bitmap bmp;
    private Sprite sprite;
    private GameLoopThread gameLoopThread;
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        //HAY QUE PONERLO POR COJONES
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        sprite.onDraw(canvas);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder){
        gameLoopThread= new GameLoopThread(this);
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.sprite_angel);
        bmp=bmp.createScaledBitmap(bmp,500,656,true);
        sprite= new Sprite(this,this.bmp);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

}
