package com.avgame.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class JukeBox {
    private static HashMap<String, Sound> sounds;
    static {
        sounds= new HashMap<String, Sound>();
    }
    public static void loadSound(String path, String name){
        Sound sound =  Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(name, sound);
    }
    public static void playSound(String name){
        sounds.get(name).play();
    }
    public static void loopSound(String name){
        sounds.get(name).loop();
    }
    public static void stopSound(String name){
        sounds.get(name).stop();
    }
    public static void disposeAllSound(){
        for(Sound s:sounds.values()){
            s.dispose();
        }
    }
    public static void stopAllSound(){
        for(Sound s:sounds.values()){
            s.stop();
        }
    }
}
