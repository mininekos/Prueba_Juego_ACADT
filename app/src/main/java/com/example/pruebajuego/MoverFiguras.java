package com.example.pruebajuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.pruebajuego.POJOS.Circulo;
import com.example.pruebajuego.POJOS.Figura;
import com.example.pruebajuego.POJOS.Imagen;
import com.example.pruebajuego.POJOS.Rectangulo;

import java.util.ArrayList;

public class MoverFiguras  extends SurfaceView implements SurfaceHolder.Callback {

    private HiloDibujo hiloDibujo;
    private ArrayList<Figura> figuras;
    private int figuraActiva;
    private int iniX;
    private int iniY;

    public MoverFiguras(Context context) {

        super(context);
        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
        figuraActiva=-1;
        iniX=0;
        iniY=0;
    }

    @Override
    public void onDraw(Canvas canvas){
        Paint p = new Paint();
        p.setAntiAlias(true);

        canvas.drawColor(Color.WHITE);

        for (Figura figura: figuras) {
            figura.onDraw(canvas);
        }

        //figuras.get(0).onDraw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for (Figura f: figuras) {
                    if(f.estaDentro(x,y)){
                        figuraActiva=f.getId();
                        iniX=(int) event.getX();
                        iniY=(int) event.getY();
                        Log.i("FiguraActiva","ID: "+f.getId());
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(figuraActiva!=-1){
                   figuras.get(figuraActiva).updatePosition(x-iniX,y-iniY);
                    iniX=(int) event.getX();
                    iniY=(int) event.getY();

                    for (Figura figura: figuras) {
                        if(!figura.isMover() )
                            figuras.get(figuraActiva);
                        if(figuras.get(figuraActiva).figuraHover(figura)) {
                            Log.i("SeTocan", "" + figuras.get(figuraActiva).figuraHover(figura));
                            figuras.get(figuraActiva).borrarFigura();
                        }
                    }


                }
                break;
            case MotionEvent.ACTION_UP:

                figuraActiva=-1;
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        int id=0;
        
        figuras= new ArrayList<Figura>();
        figuras.add(new Circulo(id++,getWidth()-200,200,Color.BLACK,Paint.Style.STROKE,105));
        figuras.add(new Rectangulo(id++,getWidth()-300,400,Color.RED,Paint.Style.STROKE,205,205));
        figuras.add(new Circulo(id++,200,200,Color.BLACK,100));
        figuras.add(new Rectangulo(id++,200,500,Color.RED,200,200));
        figuras.add(new Imagen(id++,800,800,getResources(),R.drawable.pokeball,(int) (getWidth()*0.1),(int)(getHeight()*0.2)));

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
