package com.avgame.game.gamestate;

import com.avgame.game.entities.Bullet;
import com.avgame.game.entities.Player;
import com.avgame.game.managers.GameKeys;
import com.avgame.game.managers.GameStateManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class PlayState extends GameState {
    private ShapeRenderer sr;
    private Player player;
    private ArrayList<Bullet> bullets;
    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        sr= new ShapeRenderer();
        player= new Player(bullets);

        bullets = new ArrayList<Bullet>();
    }

    @Override
    public void update(float dt) {
        //System.out.println("Updtae");
        //get use input
        handleInput();
        //updtae player
        player.update(dt);
        
        //update player bullets
        for(int i =0; i<bullets.size();i++){
            bullets.get(i).update(dt);
        }
    }

    @Override
    public void draw() {
       // System.out.println("draww");
        player.draw(sr);
    }

    @Override
    public void handleInput() {
        player.setLeft(GameKeys.isDown(GameKeys.LEFT));
        player.setRight(GameKeys.isDown(GameKeys.RIGHT));
        player.setUp(GameKeys.isDown(GameKeys.UP));
        if(GameKeys.isPressed(GameKeys.SPACE)){
            player.shoot();
        }
    }

    @Override
    public void dispose() {

    }
}
