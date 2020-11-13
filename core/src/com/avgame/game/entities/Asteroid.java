package com.avgame.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Asteroid extends SpaceOject{
    private int type;
    public static final int SMALL= 0;
    public static final int MEDIUM=1;
    public static final int LARGE = 2;
//tạo ngẩu nhiên asteroid
    private int numPoints;
    private float[] dists;

    private int score;
    private boolean remove;
    public Asteroid(float x,float y, int type){
        this.x= x;
        this.y = y;
        this.type= type;
        if(type==SMALL){
            numPoints=8;
            width=height=20;
            speed= MathUtils.random(50,60);
            score =100;
        } else if(type==MEDIUM){
            numPoints=10;
            width=height=30;
            speed= MathUtils.random(30,40);
            score= 50;
        }else if(type==LARGE){
            numPoints=12;
            width=height=50;
            speed= MathUtils.random(10,20);
            score= 20;
        }
        rotationSpeed= MathUtils.random(-1,1);
        radians= MathUtils.random(2*3.1415f);
        dX= MathUtils.cos(radians)*speed;
        dY= MathUtils.sin(radians)*speed;

        shapeX= new float[numPoints];
        shapeY= new float[numPoints];
        dists= new float[numPoints];
        int radius= (int) (width/2);
        for(int i=0;i<numPoints;i++){
            dists[i] = MathUtils.random(radius / 2, radius);
        }
        setShape();
        
    }

    private void setShape() {
        float angle =0;
        for(int i=0;i<numPoints;i++){
            shapeX[i]=x+ MathUtils.cos(angle+radians)* dists[i];
            shapeY[i]=y+ MathUtils.sin(angle+radians)* dists[i];
            angle+=2*3.1415f/numPoints;
        }
    }
    public int getScore(){
        return score;
    }
    public int getType(){return type;}
    public boolean shouldRemove(){return remove;}
    public void update(float dt){
        x+=dX*dt;
        y+= dY*dt;
        radians+= rotationSpeed*dt;
        setShape();
        wrap();
    }
    public void draw(ShapeRenderer sr){
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0, j = shapeX.length - 1; i < shapeX.length; j = i++) {
            sr.line(shapeX[i], shapeY[i], shapeX[j], shapeY[j]);
        }
        sr.end();
    }
}
