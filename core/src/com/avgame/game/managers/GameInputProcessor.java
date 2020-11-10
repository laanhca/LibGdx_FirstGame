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
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            GameKeys.setKey(GameKeys.LEFT, true);
        }
        if (keycode == Input.Keys.RIGHT) {
            GameKeys.setKey(GameKeys.RIGHT, true);
        }
        if (keycode == Input.Keys.UP) {
            GameKeys.setKey(GameKeys.UP, true);
        }
        if (keycode == Input.Keys.DOWN) {
            GameKeys.setKey(GameKeys.DOWN, true);
        }
        if (keycode == Input.Keys.ENTER) {
            GameKeys.setKey(GameKeys.ENTER, true);
        }
        if (keycode == Input.Keys.SHIFT_LEFT) {
            GameKeys.setKey(GameKeys.SHIFT, true);
        }
        if (keycode == Input.Keys.SPACE) {
            GameKeys.setKey(GameKeys.SPACE, true);
        }
        if (keycode == Input.Keys.TAB) {
            GameKeys.setKey(GameKeys.TAB, true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            GameKeys.setKey(GameKeys.LEFT, false);
        }
        if (keycode == Input.Keys.RIGHT) {
            GameKeys.setKey(GameKeys.RIGHT, false);
        }
        if (keycode == Input.Keys.UP) {
            GameKeys.setKey(GameKeys.UP, false);
        }
        if (keycode == Input.Keys.DOWN) {
            GameKeys.setKey(GameKeys.DOWN, false);
        }
        if (keycode == Input.Keys.ENTER) {
            GameKeys.setKey(GameKeys.ENTER, false);
        }
        if (keycode == Input.Keys.SHIFT_LEFT) {
            GameKeys.setKey(GameKeys.SHIFT, false);
        }
        if (keycode == Input.Keys.SPACE) {
            GameKeys.setKey(GameKeys.SPACE, false);
        }
        if (keycode == Input.Keys.TAB) {
            GameKeys.setKey(GameKeys.TAB, false);
        }
        return true;
    }
}
