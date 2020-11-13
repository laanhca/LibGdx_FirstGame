package com.avgame.game.managers;

import com.avgame.game.gamestate.GameState;
import com.avgame.game.gamestate.MenuState;
import com.avgame.game.gamestate.PlayState;

public class GameStateManager {
    public static final int MENU=0;
    public static final int PLAY=1;
    //màn chơi hiện tại
    private GameState gameState;
    public GameStateManager(){
        setState(MENU);
    }
    public void setState(int state){
        if(gameState!=null) gameState.dispose();
        if(state==MENU){
            //chuyển sang màn menu
            gameState = new MenuState(this);
        }
        if(state==PLAY){
            //chuyển sang màn chơi
            gameState = new PlayState(this);

        }

    }
    public void update(float dt){
        gameState.update(dt);
    }
    public void draw(){
        gameState.draw();
    }
}
