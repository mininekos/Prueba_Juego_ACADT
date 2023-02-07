package com.example.pruebajuego.POJOSSprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.pruebajuego.GameView;

import java.util.Random;


public class Sprite {
    private static final int BMP_ROWS=4;
    private static final int BMP_COLUMNS=3;
    private static final int MAX_SPEED=20;
    private int x=0;
    private int y=0;
    private int xSpeed;
    private int ySpeed;
    private GameView gameview;
    private Bitmap bmp;
    private int currentFrame=0;
    private int width;
    private int height;
    //direction 0 up, 1 left, 2 right, down
    //animation
    int[] DIRECTION_TO_ANIMATION_MAP={3,1,0,2};

    public Sprite(GameView gameView, Bitmap bmp){
        this.gameview=gameView;
        this.bmp=bmp;
        this.width=bmp.getWidth()/BMP_COLUMNS;
        this.height=bmp.getHeight()/BMP_ROWS;
        Random rnd=new Random();
        xSpeed=rnd.nextInt(MAX_SPEED*2)-MAX_SPEED;
        ySpeed=rnd.nextInt(MAX_SPEED*2)-MAX_SPEED;
    }

    public Sprite(GameView gameView, Bitmap bmp,int x, int y){
        this.gameview=gameView;
        this.bmp=bmp;
        this.width=bmp.getWidth()/BMP_COLUMNS;
        this.height=bmp.getHeight()/BMP_ROWS;
        Random rnd=new Random();
        xSpeed=rnd.nextInt(20)-5;
        ySpeed=rnd.nextInt(20)-5;
        this.y=y;
        this.x=x;
    }

    private void update(){
        if(x>gameview.getWidth()-width-xSpeed || x+xSpeed<0){
            xSpeed=-xSpeed;

        }
        if(y>gameview.getHeight()-height-ySpeed || y+ySpeed<0){
            ySpeed=-ySpeed;
        }
        x=x+xSpeed;
        y=y+ySpeed;
        currentFrame=++currentFrame%BMP_COLUMNS;
    }

    private int getAnimationRow(){
        double dirDouble=(Math.atan2(xSpeed,ySpeed)/(Math.PI/2)+2);
        int direction=(int) Math.round(dirDouble) % BMP_ROWS;
        return DIRECTION_TO_ANIMATION_MAP[direction];

    }

    public void onDraw(Canvas canvas){
        update();
        int srcX=currentFrame*width;
        int srcY=getAnimationRow()*height;

        Rect src=new Rect(srcX,srcY,srcX+width,srcY+height);
        Rect dst=new Rect(x,y,x+width,y+height);
        canvas.drawBitmap(bmp,src,dst,null);
    }

    public boolean isCollition(float x2, float y2){
        return x2>x && x2<x+width && y2>y && y2<y+height;
    }

}
