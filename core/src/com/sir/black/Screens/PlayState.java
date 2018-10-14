package com.sir.black.Screens;

import com.badlogic.gdx.math.Vector2;
import com.sir.black.Common.GameStateManager;
import com.sir.black.Screens.Maps.UsualMap;

/**
 * Ігрове меню гри
 * 18.01.18
 */

public class PlayState extends State {
    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        defaultValuePlayState();
    }
    public void defaultValuePlayState(){
        map = new UsualMap();
    }

    /**
     * Обновлення меню
     */
    @Override
    public void update() {
        super.update(); // Ввод, меню лічильник, обновлення камери
    }
    /**
     * Події меню
     */
    @Override
    public void eventListener(int i) {
        switch (i) {
            case (0) : { } break; // Перший елемент меню
            default: {} break;
        }
    }
}