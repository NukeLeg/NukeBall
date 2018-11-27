package com.sir.black.Screens.GameOver;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Common.GameStateManager;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.CongratulationsState.CongratulationsState;
import com.sir.black.Screens.GameState.GameState;
import com.sir.black.Screens.MainScreen.MainScreen;
import com.sir.black.Screens.MenuStateGeneral.MenuStateGeneral;
import com.sir.black.Screens.State;
import com.sir.black.Tools.Character.InitialObject.CircleObject;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Menu.Button;
import com.sir.black.Tools.Menu.Menu;

public class GameOver extends MenuStateGeneral {
    private BitmapFont bitMapFont;
    public GameOver(GameStateManager gameStateManager) {
        super(gameStateManager);
    }
    ///region initialize
    @Override
    protected void initialize() {
        super.initialize();
        bitMapFont = new BitmapFont();

        menu = new Menu(initializeButtons());
    }
    protected Button[] initializeButtons(){
        Button[] buttons = new Button[4];
        Texture[] textures = new Texture[]{Textures.hexahonal, Textures.backToMainScreen};
        Vector2[] positions = initializeBackButtonPosition(textures, 2);
        buttons[0] = new Button(
                new CircleObject[]{
                        new CircleObject(textures[0], positions[0], 2,
                                Fin.backGroundButtonColor,0.0f),
                        new CircleObject(textures[1], positions[1], 1,
                                Fin.foreGroundButtonColor, 1)
                }
        );


        textures = new Texture[]{Textures.hexahonal, Textures.musicSign, Textures.musicIndicationSign};
        positions = initializeMusicButtonPosition(textures, 1);
        buttons[1] = new Button(
                new GameObject[]{
                        new GameObject(textures[0], positions[0], 1,
                                Fin.backGroundButtonColor,0.0f),
                        new GameObject(textures[1], positions[1], 1,
                                Fin.foreGroundButtonColor, 1),
                        new GameObject(textures[2], positions[2], 1,
                                Fin.foreGroundButtonColor, 1),

                }
        );
        textures = new Texture[]{Textures.hexahonal, Textures.soundSign};
        positions = initializeSoundButtonPosition(textures, 1);
        buttons[2] = new Button(
                new GameObject[]{
                        new GameObject(textures[0], positions[0], 1,
                                Fin.backGroundButtonColor,0.0f),
                        new GameObject(textures[1], positions[1], 1,
                                Fin.foreGroundButtonColor, 1)
                }
        );

        textures = new Texture[]{Textures.hexahonal, Textures.restartArrow};
        positions = initializeRestartButtonPosition(textures, 2);
        buttons[3] = new Button(
                new CircleObject[]{
                        new CircleObject(textures[0], positions[0], 2,
                                Fin.backGroundButtonColor,0.0f),
                        new CircleObject(textures[1], positions[1], 2,
                                Fin.foreGroundButtonColor, 1)
                }
        );

        return buttons;
    }
    protected Vector2 [] initializeBackButtonPosition(Texture [] textures, float scale){
        Vector2[] positions = new Vector2[textures.length];

        for(int  i = 0; i < positions.length; i++){
            positions[i] = new Vector2(Fin.HDWidth / 2 - 250, Fin.HDHeight * 0.4f);
        }
        return positions;
    }
    protected Vector2 [] initializeRestartButtonPosition(Texture [] textures, float scale){
        Vector2[] positions = new Vector2[textures.length];

        for(int  i = 0; i < positions.length; i++){
            positions[i] = new Vector2(Fin.HDWidth / 2 + 250, Fin.HDHeight * 0.4f);
        }
        return positions;
    }
    ///endregion
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        super.render();
        Fin.spriteBatch.begin();
        if(Fin.spriteBatch.isDrawing()) {
            bitMapFont.draw(Fin.spriteBatch, "Game Over", 0.5f, 0.5f, 0, 9, 10.0f, 10, true);
        }
        Fin.spriteBatch.end();
    }

    @Override
    protected void eventListener(int i) {
        switch (i){
            case 0 : gameStateManager.push(new MainScreen(gameStateManager)); break;
            case 1 : gameStateManager.push(new GameOver(gameStateManager)); break;
            case 2 : gameStateManager.push(new CongratulationsState(gameStateManager)); break;
            case 3 : gameStateManager.push(new GameState(gameStateManager)); break;
        }
    }
}
