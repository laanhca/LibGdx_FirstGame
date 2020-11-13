package com.avgame.game.entities;

import com.avgame.game.AVGame;

public class SpaceOject {
    //position
    protected float x;
    protected float y;
    public float getx() {return x;
    }
    public float gety(){return y;}
    //vector
    protected float dX;
    protected float dY;
    //direction
    protected float radians;

    protected float speed;

    protected float rotationSpeed;
    //size
    protected float width;
    protected float height;
    //shape
    protected float[] shapeX;
    protected float[] shapeY;

    public float[] getShapeX() {
        return shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }
    public boolean intersects(SpaceOject other){
        float[] sx = other.getShapeX();
        float[] sy = other.getShapeY();
        for(int i = 0; i < sx.length; i++) {
            if(contains(sx[i], sy[i])) {
                return true;
            }
        }
        return false;
    }
    public boolean contains(float x,float y){
        boolean b = false;
        for(int i = 0, j = shapeX.length - 1;
            i < shapeX.length;
            j = i++) {
            if((shapeY[i] > y) != (shapeY[j] > y) &&
                    (x < (shapeX[j] - shapeX[i]) *
                            (y - shapeY[i]) / (shapeY[j] - shapeY[i])
                            + shapeX[i])) {
                b = !b;
            }
        }
        return b;

    }

    protected void wrap(){
        if(x < 0) x = AVGame.G_WIDTH;
        if(x > AVGame.G_WIDTH) x = 0;
        if(y < 0) y = AVGame.G_HEIGHT;
        if(y > AVGame.G_HEIGHT) y = 0;
    }

}
