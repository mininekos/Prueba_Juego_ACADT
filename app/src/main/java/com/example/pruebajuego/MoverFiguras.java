package com.example.pruebajuego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.pruebajuego.POJOS.Circulo;
import com.example.pruebajuego.POJOS.Figura;
import com.example.pruebajuego.POJOS.Rectangulo;

import java.util.ArrayList;

public class MoverFiguras  extends SurfaceView implements SurfaceHolder.Callback {

    private HiloDibujo hiloDibujo;
    private ArrayList<Figura> figuras;
    private int figuraActiva;

    public MoverFiguras(Context context) {

        super(context);
        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onDraw(Canvas canvas){
        Paint p = new Paint();
        p.setAntiAlias(true);

        canvas.drawColor(Color.WHITE);

        for (Figura figura: figuras) {
            figura.onDraw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        int id=0;

        figuras= new ArrayList<Figura>();
        figuras.add(new Circulo(id++,200,200,Color.BLACK,100));
        figuras.add(new Rectangulo(id++,200,500,Color.RED,200,200));

        hiloDibujo=new HiloDibujo( this);
        hiloDibujo.setRunning(true);
        hiloDibujo.start();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry= true;
        hiloDibujo.setRunning(false);
        while (retry){
            try {
                hiloDibujo.join();
                retry=false;
            }
            catch (InterruptedException e){}
        }
    }
}
