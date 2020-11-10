package com.avgame.game.entities;

import com.avgame.game.AVGame;

public class SpaceOject {
    //position
    protected float x;
    protected float y;
    //vector
    protected float dX;
    protected float dY;
    //direction
    protected float radians;

    protected float speed;

    protected float rotationSpeed;
    //size
    protected float with;
    protected float height;
    //shape
    protected float[] shapeX;
    protected float[] shapeY;
    protected void wrap(){
        if (x<0){
            x= AVGame.G_WITH;
        }
        if(x>AVGame.G_WITH){
            x=0;
        }
        if (y<0){
            y= AVGame.G_HEIGHT;
        }
        if(y>AVGame.G_HEIGHT){
            y=0;
        }
    }

}
