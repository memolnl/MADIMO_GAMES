package com.example.madimo_games.breakout;

import android.graphics.Canvas;
import android.graphics.Point;

public interface GameObject {
    public void draw(Canvas canvas);
    public void update();

    void update(Point point);
}
