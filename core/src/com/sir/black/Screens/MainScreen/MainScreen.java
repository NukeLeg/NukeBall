package com.sir.black.Screens.MainScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Common.GameStateManager;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.GameState.GameState;
import com.sir.black.Screens.State;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Menu.Menu;
import com.sir.black.Tools.Menu.Button;
import com.badlogic.gdx.graphics.Texture;

public class MainScreen extends State {
    /**
     * In out GameScreen state we have to draw 4 buttons
     * 0 start / continue game process
     * 3 - restart game process
     */
    private GameState gameState;
    ///region initialize
    @Override
    protected void initialize() {
        super.initialize();
        menu = new Menu(initializeButtons());
    }
    protected Button[] initializeButtons(){
        Button [] buttons = new Button[4];

        Texture[] textures = new Texture[]{Textures.hexahonal, Textures.startButtonTexture};
        Vector2[] positions = initializeStartButtonPosition(textures, 2);
        buttons[0] = new Button(
                new GameObject[]{
                        new GameObject(textures[0], positions[0], 2,
                                new Color(0.0f, 0.0f, 0.0f,1),0.0f),
                        new GameObject(textures[1], positions[1], 2,
                                new Color(1f, 1f, 1f,1), 1)
                }
        );

        textures = new Texture[]{Textures.hexahonal, Textures.musicSign, Textures.musicIndicationSign};
        positions = initializeMusicButtonPosition(textures, 1);
        buttons[1] = new Button(
                new GameObject[]{
                        new GameObject(textures[0], positions[0], 1,
                                new Color(0.0f, 0.0f, 0.0f,1),0.0f),
                        new GameObject(textures[1], positions[1], 1,
                                new Color(1f, 1f, 1f,1), 1),
                        new GameObject(textures[2], positions[2], 1,
                                new Color(1f, 1f, 1f,1), 1),

                }
        );

        textures = new Texture[]{Textures.hexahonal, Textures.soundSign};
        positions = initializeSoundButtonPosition(textures, 1);
        buttons[2] = new Button(
                new GameObject[]{
                        new GameObject(textures[0], positions[0], 1,
                                new Color(0.0f, 0.0f, 0.0f,1),0.0f),
                        new GameObject(textures[1], positions[1], 1,
                                new Color(1f, 1f, 1f,1), 1)
                }
        );
        textures = new Texture[]{Textures.hexahonal, Textures.restartArrow};
        positions = initializeRestartButtonPosition(textures, 2);
        buttons[3] = new Button(
                new GameObject[]{
                        new GameObject(textures[0], positions[0], 2,
                                new Color(0.0f, 0.0f, 0.0f,1),0.0f),
                        new GameObject(textures[1], positions[1], 2,
                                new Color(1f, 1f, 1f,1), 1)
                }
        );



        return buttons;
    }
    protected Vector2 [] initializeStartButtonPosition(Texture [] textures, float scale){
        Vector2[] positions = new Vector2[textures.length];
        for (int i = 0; i < positions.length; i++){
            positions[i] = new Vector2((Fin.HDWidth - textures[i].getWidth() * scale) / 2,
                    Fin.HDHeight / 2 - textures[i].getHeight() * scale / 2);
        }
        positions[1].x += 10;
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
    protected Vector2 [] initializeRestartButtonPosition(Texture [] textures, float scale){
        Vector2[] positions = new Vector2[textures.length];

        positions[0] = new Vector2(Fin.HDWidth / 2 + textures[0].getWidth() * scale / 4,
                    Fin.HDHeight / 2 );
        positions[1] = new Vector2(positions[0].x + textures[1].getWidth() / 2 + 5,
                positions[0].y + textures[1].getHeight() / 2 + 30);

        positions[1].x += 10;
        return positions;
    }
    ///endregion
    ///region constructor
    public MainScreen(GameStateManager gameStateManager) {
        super(gameStateManager);
    }
    public MainScreen(GameStateManager gameStateManager, GameState gameState) {
        super(gameStateManager);
        this.gameState = gameState;
    }
    ///endregion
    public GameState getGameState() {
        if(gameState!=null){
            return gameState;
        }else{
            return new GameState(gameStateManager);
        }
    }
    /**
     * In out GameScreen state we have to draw 4 buttons
     * 0 start / continue game process
     * 3 - restart game process
     */
    @Override
    protected void eventListener(int i) {
        super.eventListener(i);
        switch (i){
            case 0 : gameStateManager.push(getGameState()); break;
            case 3 : gameStateManager.push(new GameState(gameStateManager)); break;
        }
    }
}
