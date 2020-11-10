package com.avgame.game.managers;

public class GameKeys {
    public static boolean[] keys;
    public static boolean[] pkeys;

    public static final int NUM_KEYS=8;
    public static final int LEFT =0;
    public static final int RIGHT =1;
    public static final int UP =2;
    public static final int DOWN =3;
    public static final int SPACE =4;
    public static final int SHIFT =5;
    public static final int ENTER =6;
    public static final int TAB =7;
      static { //chạy một lần khi load class
        //  System.out.println("load Gamekeys");
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }
    public static void update(){

        for(int i=0;i<NUM_KEYS;i++){
            pkeys[i] = keys[i];
        }
    }
    public static void setKey(int k, boolean b){
        keys[k]=b;
    }
    public static boolean isDown(int k){
        return keys[k];
    }
    public static boolean isPressed(int k){
        return keys[k]&& !pkeys[k];
    }

}
