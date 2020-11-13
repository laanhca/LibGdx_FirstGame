package com.avgame.game.gamestate;

import com.avgame.game.AVGame;
import com.avgame.game.managers.GameKeys;
import com.avgame.game.managers.GameStateManager;
import com.avgame.game.managers.JukeBox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class MenuState extends GameState{
    SpriteBatch sb;
    BitmapFont font ;
    BitmapFont choiceFont ;
    private int choice;
    Music music;
  //  FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public MenuState(GameStateManager gsm) {
        super(gsm);
    }
    public void draw(int choice){

    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Amatic-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter= new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size= 36;

        music=Gdx.audio.newMusic(Gdx.files.internal("sounds/amadeuslegendary.ogg"));
        music.play();
        music.setLooping(true);
        font =generator.generateFont(parameter);
        choiceFont =generator.generateFont(parameter);

        choice=0;



    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        sb.begin();
        choiceFont.setColor(255,0,0,1);

        if(choice==0){
            font.draw(sb,"Asteroid", AVGame.G_WIDTH/2.2f,4.5f*AVGame.G_HEIGHT/5);
            choiceFont.draw(sb,"Play", AVGame.G_WIDTH/2.1f,4f*AVGame.G_HEIGHT/6);
            font.draw(sb,"Setting", AVGame.G_WIDTH/2.2f,3.5f*AVGame.G_HEIGHT/6);
            font.draw(sb,"Quit", AVGame.G_WIDTH/2.1f,3f*AVGame.G_HEIGHT/6);
        }
        if(choice==1){
            font.draw(sb,"Asteroid", AVGame.G_WIDTH/2.2f,4.5f*AVGame.G_HEIGHT/5);
            font.draw(sb,"Play", AVGame.G_WIDTH/2.1f,4f*AVGame.G_HEIGHT/6);
            choiceFont.draw(sb,"Setting", AVGame.G_WIDTH/2.2f,3.5f*AVGame.G_HEIGHT/6);
            font.draw(sb,"Quit", AVGame.G_WIDTH/2.1f,3f*AVGame.G_HEIGHT/6);
        }
        if(choice==2){
            font.draw(sb,"Asteroid", AVGame.G_WIDTH/2.2f,4.5f*AVGame.G_HEIGHT/5);
            font.draw(sb,"Play", AVGame.G_WIDTH/2.1f,4f*AVGame.G_HEIGHT/6);
            font.draw(sb,"Setting", AVGame.G_WIDTH/2.2f,3.5f*AVGame.G_HEIGHT/6);
            choiceFont.draw(sb,"Quit", AVGame.G_WIDTH/2.1f,3f*AVGame.G_HEIGHT/6);
        }
        sb.end();
      //  handleInput();
    }



    @Override
    public void handleInput() {
        if(GameKeys.isPressed(GameKeys.DOWN)){
            choice++;
            if(choice> 2) choice=0;
           // System.out.println(choice);
        }
        if(GameKeys.isPressed(GameKeys.UP)){
            choice--;
            if(choice< 0) choice=2;
           // System.out.println(choice);
        }
        if(GameKeys.isPressed(GameKeys.ENTER)){
//            System.out.println("enter");
            if(choice==0){

            gsm.setState(GameStateManager.PLAY);

                music.setVolume(0.4f);
            }
            if (choice==2){
               // System.exit(0);
                music.dispose();
                Gdx.app.exit();
            }


        }
    }

    @Override
    public void dispose() {
        sb.dispose();
       // music.dispose();
    }
}
