package com.sir.black.Screens.GameState;

import com.sir.black.Common.GameStateManager;
import com.sir.black.Screens.Maps.PlanetLocation;
import com.sir.black.Screens.State;

/**
 * Ігрове меню гри
 * 18.01.18
 */

public class GameState extends State {
    public GameState(GameStateManager gameStateManager) {
        super(gameStateManager);
        defaultValuePlayState();
    }
    public void defaultValuePlayState() {
        map = new PlanetLocation();
        mediator = new GameStateMediator(inputControl, camera2D, menu, map);
        camera2D.getCamera().zoom = 2.5f;
        camera2D.getCamera().position.y-=300;
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