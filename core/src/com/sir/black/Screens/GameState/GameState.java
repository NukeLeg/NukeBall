package com.sir.black.Screens.GameState;

import com.badlogic.gdx.math.Vector2;
import com.sir.black.Common.GameStateManager;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.Maps.PlanetLocation;
import com.sir.black.Screens.State;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Menu.Button;
import com.sir.black.Tools.Menu.Menu;
import com.sir.black.Tools.Special.Checker;

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
        menu = new Menu(1);
        mediator = new GameStateMediator(inputControl, camera2D, menu, map);
        camera2D.getCamera().zoom = 2.5f; // FIXME: 18.11.2018 delete
        camera2D.getCamera().position.y-=300; // FIXME: 18.11.2018 delete
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

    @Override
    public void render() {
        super.render();
    }
}