package com.avgame.game.entities;

import com.avgame.game.AVGame;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import sun.swing.BakedArrayList;

import java.util.ArrayList;

public class Player extends SpaceOject {
    private  final int MAX_BULLETS=4;

    private float[] flamex;
    private float[] flamey;

    private boolean left;
    private boolean right;
    private boolean up;

    private float maxSpeed;

    private float acceleration; //tawngg tốc
    private float deceleration; // giảm tốc
    private float acceleratingTimer;
    private ArrayList<Bullet> bullets= new ArrayList<Bullet>();

    public Player(ArrayList<Bullet> bullets) {
        this.bullets = bullets;

        x = AVGame.G_WITH / 2;
        y = AVGame.G_HEIGHT / 2;

        maxSpeed = 300;
        acceleration = 200;
        deceleration = 10;

        shapeX = new float[4];
        shapeY = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 3;

        flamex = new float[3];
        flamey = new float[3];

    }
    public void shoot(){
        if(bullets.size()>MAX_BULLETS){
            return;
        }
        bullets.add(new Bullet(x,y,radians));
    }

    private void setShape() {
        shapeX[0] = x + MathUtils.cos(radians) * 8;
        shapeY[0] = y + MathUtils.sin(radians) * 8;

        shapeX[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapeY[1] = y + MathUtils.sin(radians - 4 * 3.1415f / 5) * 8;


        shapeX[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapeY[2] = y + MathUtils.sin(radians + 3.1415f) * 5;


        shapeX[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapeY[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;

    }

    private void setFlame(){
        flamex[0] = x + MathUtils.cos(radians - 5 * 3.1415f / 6) * 5;
        flamey[0] = y + MathUtils.sin(radians - 5 * 3.1415f / 6) * 5;

        flamex[1] = x + MathUtils.cos(radians - 3.1415f) *
                (6 + acceleratingTimer * 50);
        flamey[1] = y + MathUtils.sin(radians - 3.1415f) *
                (6 + acceleratingTimer * 50);

        flamex[2] = x + MathUtils.cos(radians + 5 * 3.1415f / 6) * 5;
        flamey[2] = y + MathUtils.sin(radians + 5 * 3.1415f / 6) * 5;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void update(float dt) {
        //quay
        if (left) {
            radians += rotationSpeed * dt;
        }
        if (right) {
            radians -= rotationSpeed * dt;
        }
        //tăng tốc
        if (up) {
            dX += MathUtils.cos(radians) * acceleration * dt;
            dY += MathUtils.sin(radians) * acceleration * dt;
            acceleratingTimer+=dt;
            if (acceleratingTimer > 0.1f) {
                acceleratingTimer=0;
            }
        }else {acceleratingTimer=0;}
        //giảm tốc
        float vec = (float) Math.sqrt(dX * dX + dY * dY);
        if (vec > 0) {
            dX -= (dX / vec) * deceleration * dt;
            dY -= (dY / vec) * deceleration * dt;
            if (vec > maxSpeed) {
                dX = (dX / vec) * maxSpeed;
                dY = (dY / vec) * maxSpeed;
            }

        }
        //set positon
        x += dX * dt;
        y += dY * dt;
        //set Shape
        setShape();
        //set flame
        if(up){
            setFlame();
        }
        // xuyên màn hình
        wrap();
    }
    // draw player
    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);

        sr.begin(ShapeRenderer.ShapeType.Line);
        //draw ship
        for (int i = 0, j = shapeX.length - 1; i < shapeX.length; j = i++) {
            sr.line(shapeX[i], shapeY[i], shapeX[j], shapeY[j]);
        }
        //draw flame
        if(up){
            for (int i = 0, j = flamex.length - 1; i < flamex.length; j = i++) {
                sr.line(flamex[i], flamey[i], flamex[j], flamey[j]);
            }
        }
        sr.end();
    }


}
