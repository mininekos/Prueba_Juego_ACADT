package com.example.pruebajuego;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.pruebajuego.Menu.Barra;
import com.example.pruebajuego.POJOS.Circulo;
import com.example.pruebajuego.Menu.Control;
import com.example.pruebajuego.POJOS.Figura;
import com.example.pruebajuego.POJOS.Imagen;
import com.example.pruebajuego.Menu.Menu;
import com.example.pruebajuego.POJOS.Rectangulo;

import java.util.ArrayList;
import java.util.Random;

public class MoverFiguras  extends SurfaceView implements SurfaceHolder.Callback {

    private HiloDibujo hiloDibujo;
    private ArrayList<Figura> figuras;
    private ArrayList<Menu> menu;
    private int figuraActiva;
    private int iniX;
    private int iniY;
    private int idFigura=0;
    private int idMenu=0;
    private Context context;


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

        for (Menu componente: menu) {
            componente.onDraw(canvas);
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
                for(Menu m:menu){
                    if(m instanceof Control){
                        Control btnPresionado= (Control) m;
                        if(((Control) m).estaDentro(x,y) && btnPresionado.getNombre().equals("agregar")){
                            Random rnd = new Random();
                            Barra barra=(Barra) menu.get(0);
                            int figuraDibujar= rnd.nextInt(2)+1;
                            if(figuraDibujar==1)
                                figuras.add(new Circulo(idFigura++,
                                        rnd.nextInt(getWidth()-500),
                                        rnd.nextInt(getHeight()-(int)barra.getAncho()-200),
                                        Color.BLACK,100));
                            if(figuraDibujar==2)
                                figuras.add(new Rectangulo(idFigura++,
                                        rnd.nextInt(getWidth()-(int)(getWidth()*0.3)),
                                        rnd.nextInt(getHeight()-(int)barra.getAncho()-200),
                                        Color.RED,
                                        200,200));
                        }
                        if(((Control) m).estaDentro(x,y) && btnPresionado.getNombre().equals("salir")){
                            ((Activity) getContext()).finish();
                        }
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


        
        figuras= new ArrayList<Figura>();
        menu= new ArrayList<Menu>();

        figuras.add(new Circulo(idFigura++,getWidth()-200,200,Color.BLACK,Paint.Style.STROKE,105));
        figuras.add(new Rectangulo(idFigura++,getWidth()-300,400,Color.RED,Paint.Style.STROKE,205,205));
        figuras.add(new Circulo(idFigura++,200,200,Color.BLACK,100));
        figuras.add(new Rectangulo(idFigura++,200,500,Color.RED,200,200));
        figuras.add(new Imagen(idFigura++,800,600,getResources(),R.drawable.pokeball,(int) (getWidth()*0.1),(int)(getHeight()*0.2)));
        menu.add(new Barra(idMenu++,0,getHeight()-150,Color.BLUE,getWidth(),150));
        menu.add(new Control(idMenu++,(int)(getWidth()*0.1),(int)(getHeight()*0.885),getResources(),R.drawable.anadir,(int) (getWidth()*0.05),(int)(getHeight()*0.1),"agregar"));
        menu.add(new Control(idMenu++,(int)(getWidth()*0.8),(int)(getHeight()*0.885),getResources(),R.drawable.salir,(int) (getWidth()*0.1),(int)(getHeight()*0.1),"salir"));

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
