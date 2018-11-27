package com.sir.black.Screens.MenuStateGeneral;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Common.GameStateManager;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.State;
import com.sir.black.Tools.Character.InitialObject.CircleObject;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Menu.Button;
import com.sir.black.Tools.Menu.Menu;

/**
 * The only reason to use this class is to define sound and music button
 */
abstract public class MenuStateGeneral extends State {
    public MenuStateGeneral(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    protected void initialize() {
        super.initialize();
        menu = new Menu(initializeButtons());
    }

    abstract protected Button[] initializeButtons();
    protected Vector2 [] initializeMusicButtonPosition(Texture [] textures, float scale){
        Vector2[] positions = new Vector2[textures.length];
        for (int i = 0; i < positions.length; i++){
            positions[i] = new Vector2(Fin.HDWidth * 0.85f - textures[i].getWidth() * scale / 2,
                    Fin.HDHeight * 0.15f - textures[i].getHeight() * scale / 2);
        }
        positions[1].x = positions[1].x - 20;
        positions[2].x = positions[2].x + 20;
        return positions;
    }
    protected Vector2 [] initializeSoundButtonPosition(Texture [] textures, float scale){
        Vector2[] positions = new Vector2[textures.length];
        for (int i = 0; i < positions.length; i++){
            positions[i] = new Vector2(Fin.HDWidth * 0.15f - textures[i].getWidth() * scale / 2,
                    Fin.HDHeight * 0.15f - textures[i].getHeight() * scale / 2);
        }
        return positions;
    }
}
