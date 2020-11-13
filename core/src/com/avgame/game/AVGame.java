package com.avgame.game;

import com.avgame.game.managers.GameInputProcessor;
import com.avgame.game.managers.GameKeys;
import com.avgame.game.managers.GameStateManager;
import com.avgame.game.managers.JukeBox;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AVGame extends ApplicationAdapter {
	public static int G_WIDTH;
	public static int G_HEIGHT;
//	SpriteBatch batch;
//	Texture img;
	OrthographicCamera cam;
	GameStateManager gsm;
	
	@Override
	public void create () {
		G_WIDTH= Gdx.graphics.getWidth();
		G_HEIGHT =Gdx.graphics.getHeight();
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
		cam = new OrthographicCamera(G_WIDTH,G_HEIGHT);
		//cam.setToOrtho(false,G_WITH,G_HEIGHT);
		cam.translate(G_WIDTH/2,G_HEIGHT/2); // dịch gốc tọa độ của camera đến ví trí G_WITH/2,G_HEIGHT/2 .
		// vị trí này chính là góc tọa độ của game.
		cam.update();
		Gdx.input.setInputProcessor(new GameInputProcessor());
		gsm= new GameStateManager();

		//load sound
		JukeBox.loadSound("sounds/shoot.ogg","shoot");
		JukeBox.loadSound("sounds/explode.ogg","explode");
		JukeBox.loadSound("sounds/extralife.ogg","extralife");
		JukeBox.loadSound("sounds/thruster.ogg","thruster");
}

	@Override
	public void render () {
//test gamekeys
//		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//			System.out.println("SPACE");
//		}






		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
		GameKeys.update();
//		batch.begin();
//		batch.setProjectionMatrix(cam.combined);
//		batch.draw(img, 0, 0);
//		batch.end();
	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}
}
