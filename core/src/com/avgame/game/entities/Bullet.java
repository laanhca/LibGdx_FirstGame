package com.avgame.game.entities;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceOject {
    private float lifeTime;
    private float lifeTimer;

    private boolean remove;

    public Bullet(float x, float y, float radians){
        this.x = x;
        this.y= y;
        this.radians = radians;

        float speed =350;
        dX= MathUtils.cos(radians)*speed;
        dY= MathUtils.sin(radians)*speed;
        with= height = 2;
        lifeTime=1;
        lifeTimer=0;
    }
    public boolean shouldRemove(){
        return  remove;
    }
    public void update(float dt){
            x += dX*dt;
            y+= dY+dt;
            wrap();
            lifeTimer+= dt;
            if(lifeTimer>lifeTime){
                remove=true;
            }
    }
    public void draw(ShapeRenderer sr){

    }

}
