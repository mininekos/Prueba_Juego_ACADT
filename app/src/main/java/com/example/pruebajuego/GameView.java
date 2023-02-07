package com.example.pruebajuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.pruebajuego.Hilos.GameLoopThread;
import com.example.pruebajuego.POJOSSprite.Sprite;
import com.example.pruebajuego.POJOSSprite.TempSprite;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private Bitmap bmp;
    private Bitmap bmpBlood;
    private Sprite sprite;
    private ArrayList<Sprite> sprites;
    private List<TempSprite> temps;
    private GameLoopThread gameLoopThread;
    private long lastClick=0;
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        //HAY QUE PONERLO POR COJONES
        setBackgroundColor(Color.WHITE);
        sprites=new ArrayList<Sprite>();
        temps=new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        for (Sprite sprite:sprites) {
            sprite.onDraw(canvas);
        }
        for (int i=temps.size()-1;i>=0;i--){
            temps.get(i).onDraw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        if (System.currentTimeMillis()-lastClick>500){
            lastClick=System.currentTimeMillis();
            float x=event.getX();
            float y=event.getY();
            for (int i=sprites.size()-1;i>=0;i--){
                Sprite sprite = sprites.get(i);
                if (sprite.isCollition(x,y)){
                    sprites.remove(sprite);
                    temps.add(new TempSprite(temps,this,x,y,bmpBlood));
                    //return false;
                    break;
                }
            }
        }


        return false;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder){

        createSprites();
        bmpBlood=BitmapFactory.decodeResource(getResources(),R.drawable.blood1);
        gameLoopThread= new GameLoopThread(this);
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
//        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.sprite_angel);
//        bmp=bmp.createScaledBitmap(bmp,500,656,true);

    }
    private void createSprites(){
        sprites.add(createSprite(R.drawable.sprite_angel));
        sprites.add(createSprite(R.drawable.sprite_angel));
        sprites.add(createSprite(R.drawable.sprite_angel));
        sprites.add(createSprite(R.drawable.sprite_angel));
        sprites.add(createSprite(R.drawable.angel_malo));
        sprites.add(createSprite(R.drawable.bad1));
        sprites.add(createSprite(R.drawable.bad2));
        sprites.add(createSprite(R.drawable.bad3));
        sprites.add(createSprite(R.drawable.bad4));
        sprites.add(createSprite(R.drawable.bad5));
        sprites.add(createSprite(R.drawable.bad6));
        sprites.add(createSprite(R.drawable.good1));
        sprites.add(createSprite(R.drawable.good2));
        sprites.add(createSprite(R.drawable.good3));
        sprites.add(createSprite(R.drawable.good4));
        sprites.add(createSprite(R.drawable.good5));
        sprites.add(createSprite(R.drawable.good6));

    }
    private Sprite createSprite(int resource){
        bmp= BitmapFactory.decodeResource(getResources(),resource);
        bmp=bmp.createScaledBitmap(bmp,500,656,true);
        return  new Sprite(this,bmp);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

}
