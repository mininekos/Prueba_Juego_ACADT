package com.example.pruebajuego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Vista extends View implements View.OnTouchListener{

    private Boolean dibujando;
    private float x,y;

    public Vista(Context context) {
        super(context);
        this.setOnTouchListener(this);
        dibujando=false;
        x=50;
        y=50;

    }
    @Override
    protected void onDraw(Canvas canvas){
        Random rnd=new Random();
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawPaint(paint);
        canvas.drawRGB(rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
//        paint.setColor(Color.BLACK);
//        paint.setAntiAlias(true);
//        canvas.drawRect(16,16,getWidth()-16,getHeight()-16,paint);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawCircle(x,y,50,paint);



    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
//        if(event.getAction()==MotionEvent.ACTION_UP){
//            Log.i("Up","Up");
//            Canvas canvas = new Canvas();
//            Paint paint = new Paint();
//            paint.setColor(Color.YELLOW);
//            paint.setAntiAlias(true);
//            canvas.drawCircle(event.getX(),event.getY(),50,paint);
//            return true;
//        }
        switch ( event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("Down","Down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("Move","Move X: "+event.getX()+" Y: "+event.getY());
                x=event.getX();
                y=event.getY();
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("Cancel","Cancel");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("Up","Up");
                break;
        }
        this.invalidate();
        return true;
    }
}
