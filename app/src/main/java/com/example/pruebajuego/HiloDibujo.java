package com.example.pruebajuego;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class HiloDibujo extends Thread{

    private SurfaceView vista;
    private SurfaceHolder sh;
    private boolean run;

    public HiloDibujo(SurfaceView vista){
        this.sh=vista.getHolder();
        this.vista=vista;
        run=false;
    }

    public void setRunning(boolean run){
        this.run=run;
    }

    public void run(){
        Canvas canvas;
        while (run) {
            canvas=null;
            try {
                canvas=sh.lockCanvas();
                //Internamente llama al metodo onDraw de la vista
                if(canvas!=null) {
                    synchronized (sh) {
                        vista.postInvalidate();
                    }
                }
            }finally {
                if(canvas!=null)
                    sh.unlockCanvasAndPost(canvas);
            }
        }
    }


}
