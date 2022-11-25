package com.example.madimo_games.breakout;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.TextureView;


public class Bricks implements GameObject{
    private Rect rectangle;
    private int color;

    public Rect getRectangle(){
        return rectangle;
    }

    public Bricks(int left, int right, int top, int bottom, int color){
        this.color = color;
        rectangle = new Rect(left, right, top, bottom);
    } //Constructor

    public boolean ballCollide(Ball pelota){
        return Rect.intersects(rectangle, pelota.getRectangle());
    } //Colision

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);
    } //Dibujar en pantalla

    @Override
    public void update() {

    }

    @Override
    public void update(Point point) {

    }
}
