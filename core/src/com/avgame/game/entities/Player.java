package com.avgame.game.entities;

import com.avgame.game.AVGame;
import com.avgame.game.managers.JukeBox;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.sun.scenario.effect.impl.prism.PrRenderInfo;
import sun.swing.BakedArrayList;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Player extends SpaceOject {
    private  final int MAX_BULLETS=4;
    private ArrayList<Bullet> bullets;
    private float[] flamex;
    private float[] flamey;

    private boolean left;
    private boolean right;
    private boolean up;

    private float maxSpeed;

    private float acceleration; //tawngg tốc
    private float deceleration; // giảm tốc
    private float acceleratingTimer;

    private boolean hit;
    private boolean dead;

    private float hitTimer;
    private float hitTime;

    private Line2D.Float[] hitLines;
    private Point2D.Float[] hitLinesVector;

    private long score;
    private int extraLives;
    private long requiredScore;

    public Player(ArrayList<Bullet> bullets) {
        this.bullets = bullets;

        x = AVGame.G_WIDTH / 2;
        y = AVGame.G_HEIGHT / 2;

        maxSpeed = 300;
        acceleration = 200;
        deceleration = 10;

        shapeX = new float[4];
        shapeY = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 3;

        hit=false;
        hitTimer=0;
        hitTime=2;

        flamex = new float[3];
        flamey = new float[3];

        score=0;
        extraLives= 3;
        requiredScore = 1000;

    }
    public void reset() {
        x = AVGame.G_WIDTH / 2;
        y = AVGame.G_HEIGHT / 2;
        setShape();
        hit = dead = false;
    }
    public long getScore(){ return score;}

    public int getExtraLives(){
        return extraLives;
    }
    public void loseLife(){
         extraLives--;
    }
    public void incrementScore(long sc){
        score+=sc;
    }
    public boolean isHit(){
        return hit;
    }
    public boolean isDead(){return dead;}
    public void shoot(){
        if(bullets.size() == MAX_BULLETS) return;
        JukeBox.playSound("shoot");
        bullets.add(new Bullet(x, y, radians));

    }

    public void hit() {
        if(hit) return;

        hit = true;
        dX = dY = 0;
        left = right = up = false;
//        Jukebox.stop("thruster");

        hitLines = new Line2D.Float[4];
        for(int i = 0, j = hitLines.length - 1;
            i < hitLines.length;
            j = i++) {
            hitLines[i] = new Line2D.Float(
                    shapeX[i], shapeY[i], shapeX[j], shapeY[j]
            );
        }

        hitLinesVector = new Point2D.Float[4];
        hitLinesVector[0] = new Point2D.Float(
                MathUtils.cos(radians + 1.5f),
                MathUtils.sin(radians + 1.5f)
        );
        hitLinesVector[1] = new Point2D.Float(
                MathUtils.cos(radians - 1.5f),
                MathUtils.sin(radians - 1.5f)
        );
        hitLinesVector[2] = new Point2D.Float(
                MathUtils.cos(radians - 2.8f),
                MathUtils.sin(radians - 2.8f)
        );
        hitLinesVector[3] = new Point2D.Float(
                MathUtils.cos(radians + 2.8f),
                MathUtils.sin(radians + 2.8f)
        );
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
        //System.out.println("up"+b);
        if(b && !up && !hit){
            JukeBox.loopSound("thruster");
        } else if(!b){
            JukeBox.stopSound("thruster");
        }
        up = b;
    }

    public void update(float dt) {
        if(hit) {
            JukeBox.stopAllSound();
            hitTimer += dt;
            if(hitTimer > hitTime) {
                dead = true;
                hitTimer = 0;
            }
            for(int i = 0; i < hitLines.length; i++) {
                hitLines[i].setLine(
                        hitLines[i].x1 + hitLinesVector[i].x * 10 * dt,
                        hitLines[i].y1 + hitLinesVector[i].y * 10 * dt,
                        hitLines[i].x2 + hitLinesVector[i].x * 10 * dt,
                        hitLines[i].y2 + hitLinesVector[i].y * 10 * dt
                );
            }
            return;
        }
        //check extraLives
        if(score>= requiredScore){
            JukeBox.playSound("extralife");
            extraLives++;
            requiredScore+= requiredScore  ;

        }
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
        //check if hit
        if(hit) {
            for(int i = 0; i < hitLines.length; i++) {
                sr.line(
                        hitLines[i].x1,
                        hitLines[i].y1,
                        hitLines[i].x2,
                        hitLines[i].y2
                );
            }
            sr.end();
            return;
        }
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
