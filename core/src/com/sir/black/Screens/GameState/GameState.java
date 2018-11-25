package com.sir.black.Screens.GameState;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Common.GameStateManager;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.MainScreen.MainScreen;
import com.sir.black.Screens.Maps.PlanetLocation;
import com.sir.black.Screens.State;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Menu.Button;
import com.sir.black.Tools.Menu.Menu;
import com.sir.black.Tools.Special.Checker;
import com.badlogic.gdx.graphics.Texture;
/**
 * Ігрове меню гри
 * 18.01.18
 */

public class GameState extends State {
    public GameState(GameStateManager gameStateManager) {
        super(gameStateManager);
        defaultValuePlayState();
    }
    ///region initialize
    public void defaultValuePlayState() {

        menu = new Menu(initializeButtons());

        map = new PlanetLocation();
        mediator = new GameStateMediator(inputControl, camera2D, menu, map);
        camera2D.getCamera().zoom = 2.5f; // FIXME: 18.11.2018 delete
        camera2D.getCamera().position.y-=300; // FIXME: 18.11.2018 delete
    }
    protected Button[] initializeButtons(){
        Button [] buttons = new Button[1];
        Texture[] textures = new Texture[]{Textures.pauseSign};
        Vector2[] positions = initializePauseButtonPosition(textures);
        buttons[0] = new Button(
                new GameObject[]{
                        new GameObject(textures[0], positions[0], 1, new Color(0, 0, 0, 1), 1)
                }
        );
        return buttons;
    }
    Vector2[] initializePauseButtonPosition(Texture[] textures){
        Vector2[] positions = new Vector2[textures.length];
        for(int i = 0; i < positions.length; i++) {
            positions[i] = new Vector2(Fin.HDWidth * 0.85f - textures[i].getWidth() / 2,
                    Fin.HDHeight *  0.85f - textures[i].getHeight()/ 2);
        }
        return positions;
    }
    /// endregion
    /**
     * Обновлення меню
     */
    @Override
    public void update() {
        super.update(); // Ввод, меню лічильник, обновлення камери
    }

    /**
     * Menu events
     * 0 MainScreen button
     */
    @Override
    public void eventListener(int i) {
        switch (i) {
            case (0) : {
                this.pauseOn();
                gameStateManager.push(new MainScreen(gameStateManager, this));
            } break; // Перший елемент меню
            default: {} break;
        }
    }

    @Override
    public void render() {
        super.render();
    }
}