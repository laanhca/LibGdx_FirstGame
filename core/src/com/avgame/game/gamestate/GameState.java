package com.avgame.game.gamestate;

import com.avgame.game.managers.GameStateManager;

public abstract class GameState  {
    protected GameStateManager gsm;
    public GameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }
    //khởi tạo màn chơi
    public abstract void init();
    //cập nhật các gtri biến, sẽ gọi khi render trò chơi
    public abstract void update(float dt);
    // vẽ trò chơi, sẽ gọi khi render trò chơi
    public abstract void draw();
    // xử lý phím từ ng chơi
    public abstract void handleInput();
    //xóa khi kết thúc màn chơi
    public abstract void dispose();
}
