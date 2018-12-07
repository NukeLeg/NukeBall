package com.sir.black.Screens.MenuStateGeneral;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Common.GameStateManager;
import com.sir.black.Data.Fin;
import com.sir.black.Data.MusicRuler;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.GameState.GameStateMediator;
import com.sir.black.Screens.Mediator.Mediator;
import com.sir.black.Screens.State;
import com.sir.black.Tools.Character.InitialObject.CircleObject;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Menu.Button;
import com.sir.black.Tools.Menu.Checkbox;
import com.sir.black.Tools.Menu.Menu;

/**
 * The only reason to use this class is to define sound and music button
 */
abstract public class MenuStateGeneral extends State {
    protected static final byte START = 0;
    protected static final byte BACK = 0;
    protected static final byte MUSIC = 1;
    protected static final byte SOUND = 2;
    protected static final byte CONTINUE = 3;
    protected static final byte NEXTSTAGE = 3;
    public MenuStateGeneral(GameStateManager gameStateManager) {
        super(gameStateManager);
    }
    public MenuStateGeneral(GameStateManager gameStateManager, Checkbox[] checkboxes) {
        super(gameStateManager);
        reinitializeSoundAndMusicButtons(checkboxes);
    }
    ///region initialize
    @Override
    protected void initialize() {
        super.initialize();
        menu = new Menu(initializeButtons());
    }

    protected Button[] initializeButtons(){
        /**
         * 3 buttons : Start, music and sound
         */
        Button[] buttons = new Button[3];
        buttons[START] = initializeStartButton();
        buttons[MUSIC] = initializeMusicButton();
        buttons[SOUND] = initializeSoundButton();
        return buttons;
    }
    protected Vector2 [] initializeStartButtonPosition(Texture [] textures, float scale){
        Vector2[] positions = new Vector2[textures.length];
        for (int i = 0; i < positions.length; i++){
            positions[i] = new Vector2(Fin.HDWidth  / 2,
                    Fin.HDHeight / 2);
        }
        return positions;
    }
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
    protected Button initializeStartButton(){
        Texture[] textures = new Texture[]{Textures.hexahonal, Textures.startButtonTexture};
        Vector2[] positions = initializeStartButtonPosition(textures, 2);
        return new Button(
                new CircleObject[]{
                        new CircleObject(textures[0], positions[0], 2, Fin.backGroundButtonColor, 0),
                        new CircleObject(textures[1], positions[1], 2,
                                Fin.foreGroundButtonColor, 1)
                }
        );
    }
    protected Button initializeMusicButton(){
        Texture [] textures = new Texture[]{Textures.hexahonal, Textures.musicSign, Textures.musicIndicationSign};
        Vector2 [] positions = initializeMusicButtonPosition(textures, 1);
        return new Checkbox(
                new GameObject[]{
                        new GameObject(textures[0], positions[0], 1,
                                Fin.backGroundButtonColor,0.0f),
                        new GameObject(textures[1], positions[1], 1,
                                Fin.foreGroundButtonColor, 1),
                        new GameObject(textures[2], positions[2], 1,
                                Fin.foreGroundButtonColor, 1),

                },
                new GameObject(Textures.badlogic, positions[0]),
                MusicRuler.isPlaying()
        );
    }
    protected Button initializeSoundButton(){
        Texture [] textures = new Texture[]{Textures.hexahonal, Textures.soundSign};
        Vector2 [] positions = initializeSoundButtonPosition(textures, 1);
        return new Button(
                new GameObject[]{
                        new GameObject(textures[0], positions[0], 1,
                                Fin.backGroundButtonColor,1.0f),
                        new GameObject(textures[1], positions[1], 1,
                                Fin.foreGroundButtonColor, 1)
                }
        );
    }
    protected void reinitializeSoundAndMusicButtons(Checkbox[] checkboxes){
        menu.setButton(checkboxes[0], 1);
        menu.setButton(checkboxes[1], 2);
    }
    ///endregion
}
