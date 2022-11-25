package com.example.madimo_games.breakout;


import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class BricksManager {
    public ArrayList<Bricks> bricks;
    private ArrayList<Colores> colores;
    private int left;  //Posicion X de Objeto
    private int right; //Posicion Y de Objeto
    private int top;   //Ancho de Objeto
    private int bottom; //Largo de Objeto
    private int color;  //Color de Objeto
    int cont=0;
    Colores color1,color2,color3,color4,color5,color6,color7,color8,color9;

    public BricksManager(int left, int right, int top, int bottom){
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        //this.color = color;
        colores = new ArrayList<>();
        bricks = new ArrayList<>();

        llenarBricks();

    } //Constructor

    private void llenarColores(){
        colores.add(color1 = new Colores(11,80,79));
        colores.add(color2 = new Colores(33,80,79));
        colores.add(color3 = new Colores(55,80,79));
        colores.add(color4 = new Colores(77,80,79));
        colores.add(color5 = new Colores(99,80,79));
        colores.add(color6 = new Colores(121,80,79));
        colores.add(color7 = new Colores(143,80,79));
        colores.add(color8 = new Colores(165,80,79));
        colores.add(color9 = new Colores(177,80,79));

    }

    private void llenarBricks() {
        llenarColores();

        int posX = 5, posY= 5, ancho= 175, altura = 50;
        for(int row = 0; row < 9; row++){
            for(int columns = 0; columns < 6; columns++){

                bricks.add(new Bricks(posX, posY, posX+ancho ,posY+altura,
                        Color.rgb(colores.get(cont).getR(),colores.get(cont).getG(),colores.get(cont).getB())));

                posX+=179;

            }
            cont++;
            posX=5;
            posY+=55;
        }

    } //Llena ArrayList de Bricks
    GamePanel gamePanel;
    public void borrarBricks(Ball pelota){
        for(Bricks bl : bricks){
            if(bl.ballCollide(pelota))
                bricks.remove(bricks.indexOf(bl));
        }
    }

    public boolean pelotaCollide(Ball pelota){
        for(Bricks bl : bricks){
            if(bl.ballCollide(pelota))
                return true;
        }
        return false;
    }

    public void update(){

    } //Actualizar

    public void draw(Canvas canvas){
        for(Bricks bl : bricks)
            bl.draw(canvas);
    } //Dibuja en canvas

}
