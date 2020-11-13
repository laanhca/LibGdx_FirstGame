package com.avgame.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;


public class GameInputProcessor extends InputAdapter { //có thể implement trực tiếp InputProcessor nhưng làm vậy
    // thì ta cần phải override tất cả phương thức trong đó. InputAdapter thực ra là lớp implement InputProcessor, ta có thể
    // override phương thức mà ta muốn
//    static {
//        System.out.println("load GameInput");
//    }
    public boolean keyDown(int k) {
        if(k == Input.Keys.UP) {
            GameKeys.setKey(GameKeys.UP, true);
        }
        if(k == Input.Keys.LEFT) {
            GameKeys.setKey(GameKeys.LEFT, true);
        }
        if(k == Input.Keys.DOWN) {
            GameKeys.setKey(GameKeys.DOWN, true);
        }
        if(k == Input.Keys.RIGHT) {
            GameKeys.setKey(GameKeys.RIGHT, true);
        }
        if(k == Input.Keys.ENTER) {
            GameKeys.setKey(GameKeys.ENTER, true);
        }
        if(k == Input.Keys.ESCAPE) {
            GameKeys.setKey(GameKeys.ESCAPE, true);
        }
        if(k == Input.Keys.SPACE) {
            GameKeys.setKey(GameKeys.SPACE, true);
        }
        if(k == Input.Keys.SHIFT_LEFT || k == Input.Keys.SHIFT_RIGHT) {
            GameKeys.setKey(GameKeys.SHIFT, true);
        }
        return true;
    }

    public boolean keyUp(int k) {
        if(k == Input.Keys.UP) {
            GameKeys.setKey(GameKeys.UP, false);
        }
        if(k == Input.Keys.LEFT) {
            GameKeys.setKey(GameKeys.LEFT, false);
        }
        if(k == Input.Keys.DOWN) {
            GameKeys.setKey(GameKeys.DOWN, false);
        }
        if(k == Input.Keys.RIGHT) {
            GameKeys.setKey(GameKeys.RIGHT, false);
        }
        if(k == Input.Keys.ENTER) {
            GameKeys.setKey(GameKeys.ENTER, false);
        }
        if(k == Input.Keys.ESCAPE) {
            GameKeys.setKey(GameKeys.ESCAPE, false);
        }
        if(k == Input.Keys.SPACE) {
            GameKeys.setKey(GameKeys.SPACE, false);
        }
        if(k == Input.Keys.SHIFT_LEFT || k == Input.Keys.SHIFT_RIGHT) {
            GameKeys.setKey(GameKeys.SHIFT, false);
        }
        return true;
    }
}
