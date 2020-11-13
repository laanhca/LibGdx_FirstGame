package com.avgame.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Particle extends SpaceOject{
    private float time;
    private float timer;
    private boolean remove;

    public Particle(float x, float y){
        this.x = x;
        this.y = y;
        width = height=2;
        speed = 50;
        radians = MathUtils.random(2*3.1415f);
        dX = MathUtils.cos(radians) * speed;
        dY = MathUtils.sin(radians) * speed;
        timer=0;
        time=1;

    }
    public boolean shouldRemove(){
        return remove;
    }
    public void update(float dt){
        x += dX * dt;
        y += dY * dt;
        timer += dt;
        if(timer>timer){
            remove=true;
        }
    }
    public void draw(ShapeRenderer sr){
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.circle(x - width / 2, y - height / 2, width / 2);

        sr.end();
    }
}
