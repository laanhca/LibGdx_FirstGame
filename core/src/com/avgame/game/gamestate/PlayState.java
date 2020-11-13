package com.avgame.game.gamestate;

import com.avgame.game.AVGame;
import com.avgame.game.entities.Asteroid;
import com.avgame.game.entities.Bullet;
import com.avgame.game.entities.Particle;
import com.avgame.game.entities.Player;
import com.avgame.game.managers.GameKeys;
import com.avgame.game.managers.GameStateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class PlayState extends GameState {
    private SpriteBatch sb;
    private BitmapFont font;
    private ShapeRenderer sr;
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Asteroid> asteroids;

    private ArrayList<Particle> particles;
    private int level;
    private int totalAsteroids;
    private int numAsteroidsLeft;
    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Hyperspace Bold.ttf"));
       FreeTypeFontGenerator.FreeTypeFontParameter parameter= new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=20;
        parameter.borderColor= Color.BLUE;
        font = generator.generateFont(parameter);
       // font = new BitmapFont(Gdx.files.internal("fonts/Hyperspace Bold.fnt"),Gdx.files.internal("fonts/Hyperspace Bold.png"),false);
        sr= new ShapeRenderer();
        bullets = new ArrayList<Bullet>();
        player= new Player(bullets);
        asteroids= new ArrayList<Asteroid>();
//        asteroids.add(new Asteroid(100,100,Asteroid.LARGE));
//        asteroids.add(new Asteroid(200,100,Asteroid.MEDIUM));
//        asteroids.add(new Asteroid(300,100,Asteroid.SMALL));

        particles = new ArrayList<Particle>();


        level=1;
        spawnAsteroids();
    }
    private void createParticles(float x, float y){
        for(int i= 0; i<6;i++){
            particles.add(new Particle(x, y));

        }
    }

    private void splitAsteroids(Asteroid a) {



        createParticles(a.getx(), a.gety());
        numAsteroidsLeft--;
        //player.incrementScore();
//        currentDelay = ((maxDelay - minDelay) *
//                numAsteroidsLeft / totalAsteroids)
//                + minDelay;
        if(a.getType() == Asteroid.LARGE) {
            asteroids.add(
                    new Asteroid(a.getx(), a.gety(), Asteroid.MEDIUM));
            asteroids.add(
                    new Asteroid(a.getx(), a.gety(), Asteroid.MEDIUM));
        }
        if(a.getType() == Asteroid.MEDIUM) {
            asteroids.add(
                    new Asteroid(a.getx(), a.gety(), Asteroid.SMALL));
            asteroids.add(
                    new Asteroid(a.getx(), a.gety(), Asteroid.SMALL));
        }
    }

    private void spawnAsteroids() {
        asteroids.clear();
        int numToSpawn = 4+level-1;
        totalAsteroids = numToSpawn*7;
        numAsteroidsLeft= totalAsteroids;
        for(int i=0;i<numToSpawn;i++){
            float x= MathUtils.random(AVGame.G_WIDTH);
            float y= MathUtils.random(AVGame.G_HEIGHT);
            float dx= x- player.getx();
            float dy = y- player.gety();
            float dists = (float) Math.sqrt(dx * dx + dy * dy);
            while (dists<100){
                 x= MathUtils.random(AVGame.G_WIDTH);
                 y= MathUtils.random(AVGame.G_HEIGHT);
                 dx= x- player.getx();
                 dy = y- player.gety();
                 dists = (float) Math.sqrt(dx * dx + dy * dy);
            }
            asteroids.add(new Asteroid(x, y, Asteroid.LARGE));
        }
    }

    @Override
    public void update(float dt) {
        //System.out.println("Updtae");
        //get use input
        handleInput();

        //next level
        if(asteroids.size()==0){
            level++;
            spawnAsteroids();
        }

        //updtae player
        player.update(dt);
        if(player.isDead()){
//            if(player.getLives() == 0) {
                //Jukebox.stopAll();
//                Save.gd.setTenativeScore(player.getScore());
//                gsm.setState(GameStateManager.GAMEOVER);
            player.reset();
            player.loseLife();
            return;
        }
        
        //update player bullets
        for(int i =0; i<bullets.size();i++){
            bullets.get(i).update(dt);
            if(bullets.get(i).shouldRemove()){
                bullets.remove(i);
                i--;
            }
        }
        //update asteroids
        for(int i =0; i<asteroids.size();i++){
            asteroids.get(i).update(dt);
            if(asteroids.get(i).shouldRemove()){
                asteroids.remove(i);
                i--;
            }
        }
        //update particle
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update(dt);
            if(particles.get(i).shouldRemove()){
                particles.remove(i);
                i--;
            }
        }
        //check va chạm
        checkCollisions();
    }

    private void checkCollisions() {
        // player-asteroid collision
        if(!player.isHit()) {
            for(int i = 0; i < asteroids.size(); i++) {
                Asteroid a = asteroids.get(i);
                if(a.intersects(player)) {
                    player.hit();
                    asteroids.remove(i);
                    i--;
                    splitAsteroids(a);

                    //Jukebox.play("explode");
                    break;
                }
            }
       }
        //bullet - asteroid collision
        for(int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            for(int j = 0; j < asteroids.size(); j++) {
                Asteroid a = asteroids.get(j);
                if(a.contains(b.getx(), b.gety())) {
                    bullets.remove(i);
                    i--;
                    asteroids.remove(j);
                    j--;
                    splitAsteroids(a);
                    //player score
                    player.incrementScore(a.getScore());
                    //Jukebox.play("explode");
                    break;
                }
            }
        }
    }

    @Override
    public void draw() {
       // System.out.println("draww");
        player.draw(sr);
        //draw bullet
        for (int i= 0;i<bullets.size();i++){
            bullets.get(i).draw(sr);
        }
        //draw asteroids
        for (int i= 0;i<asteroids.size();i++){
            asteroids.get(i).draw(sr);
        }
        //draw particle
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).draw(sr);
        }
        //draw score and extralive
        sb.setColor(1,1,1,1);
        sb.begin();
        font.draw(sb,Long.toString(player.getScore()),10,AVGame.G_HEIGHT-10);
        font.draw(sb,Long.toString(player.getExtraLives()),AVGame.G_WIDTH-20,AVGame.G_HEIGHT-10);
        sb.end();
    }

    @Override
    public void handleInput() {
        if(!player.isHit()) {
            player.setLeft(GameKeys.isDown(GameKeys.LEFT));
            player.setRight(GameKeys.isDown(GameKeys.RIGHT));
            player.setUp(GameKeys.isDown(GameKeys.UP));
            if(GameKeys.isPressed(GameKeys.SPACE)) {
                player.shoot();
            }
        }
    }

    @Override
    public void dispose() {

    }
}
