package com.sir.black.Screens.MainScreen;

import com.badlogic.gdx.math.Vector2;
import com.sir.black.Common.GameStateManager;
import com.sir.black.Data.Fin;
import com.sir.black.Data.MusicRuler;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.CongratulationsState.CongratulationsState;
import com.sir.black.Screens.GameOver.GameOver;
import com.sir.black.Screens.GameState.GameState;
import com.sir.black.Screens.MenuStateGeneral.MenuStateGeneral;
import com.sir.black.Tools.Character.InitialObject.CircleObject;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Menu.Checkbox;
import com.sir.black.Tools.Menu.Menu;
import com.sir.black.Tools.Menu.Button;
import com.badlogic.gdx.graphics.Texture;

public class MainScreen extends MenuStateGeneral {
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
    @Override
    protected Button[] initializeButtons(){
        Button [] buttons = new Button[4];
        buttons[0] = initializeStartButton();
        buttons[1] = initializeMusicButton();
        buttons[2] = initializeSoundButton();
        return buttons;
    }
    protected void initializeContinueGameButton(){

        if (gameState!=null) {
            Texture[] textures = new Texture[]{Textures.hexahonal, Textures.restartArrow};
            Vector2[] positions = initializeContinueButtonPosition(textures, 2);
            menu.setButton(
                    new Button(
                            new CircleObject[]{
                                    new CircleObject(textures[0], positions[0], 2,
                                        Fin.backGroundButtonColor, 0.0f),
                                    new CircleObject(textures[1], positions[1], 2,
                                        Fin.foreGroundButtonColor, 1)
                            }
                    ),
                    3
            );
        }
    }

    protected Vector2 [] initializeContinueButtonPosition(Texture [] textures, float scale){
        Vector2[] positions = new Vector2[textures.length];

        positions[0] = new Vector2(Fin.HDWidth / 2 + 320,
                    Fin.HDHeight / 2 + 190);
        positions[1] = new Vector2(Fin.HDWidth / 2 + 320,
                Fin.HDHeight / 2 + 190);
        return positions;
    }
    ///endregion
    ///region constructor
    public MainScreen(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    public MainScreen(GameStateManager gameStateManager, Checkbox[] checkboxes) {
        super(gameStateManager, checkboxes);
    }

    public MainScreen(GameStateManager gameStateManager, GameState gameState) {
        super(gameStateManager);
        this.gameState = gameState;
        this.initializeContinueGameButton();
    }
    public MainScreen(GameStateManager gameStateManager,  Checkbox[]checkboxes, GameState gameState) {
        super(gameStateManager, checkboxes);
        this.gameState = gameState;
        this.initializeContinueGameButton();
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
     * 2 music oof/on
     * 3 - restart game process
     */
    @Override
    protected void eventListener(int i) {
        switch (i){
            case 0 : gameStateManager.push(getGameState()); break;
            case 1 : MusicRuler.switchMusic();  break;
            case 2 :  break;
            case 3 : gameStateManager.push(new GameState(gameStateManager)); break;
        }
    }
}
