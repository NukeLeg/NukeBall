package com.sir.black.Screens.CongratulationsState;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Common.GameStateManager;
import com.sir.black.Data.Fin;
import com.sir.black.Data.MusicRuler;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.GameOver.GameOver;
import com.sir.black.Screens.GameState.GameState;
import com.sir.black.Screens.MainScreen.MainScreen;
import com.sir.black.Screens.MenuStateGeneral.MenuStateGeneral;
import com.sir.black.Screens.State;
import com.sir.black.Tools.Character.Behaviour.Behaviour;
import com.sir.black.Tools.Character.Behaviour.HappyBall;
import com.sir.black.Tools.Character.Character;
import com.sir.black.Tools.Character.InitialObject.CircleObject;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Character.PlanetDestroyer;
import com.sir.black.Tools.Menu.Button;
import com.sir.black.Tools.Menu.Checkbox;
import com.sir.black.Tools.Menu.Menu;
import com.sir.black.Screens.SupportState.Map;

public class CongratulationsState extends MenuStateGeneral {
    public CongratulationsState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    public CongratulationsState(GameStateManager gameStateManager, Checkbox[] checkboxes) {
        super(gameStateManager, checkboxes);
    }

    ///region initialize
    @Override
    protected void initialize() {
        super.initialize();
        menu = new Menu(initializeButtons());
        map = initializeMap();

    }
    @Override
    protected Button[] initializeButtons(){
        Button[] buttons = new Button[4];
        buttons[BACK] = initializeBackButton();
        buttons[MUSIC] = initializeMusicButton();
        buttons[SOUND] = initializeSoundButton();
        buttons[NEXTSTAGE] = initializeNextStageButton();
        return buttons;
    }
    protected Vector2 [] initializeBackButtonPosition(Texture [] textures, float scale){
        Vector2[] positions = new Vector2[textures.length];

        for(int  i = 0; i < positions.length; i++){
            positions[i] = new Vector2(Fin.HDWidth / 2 - 250, Fin.HDHeight * 0.4f);
        }
        return positions;
    }
    protected Vector2 [] initializeNextStageButtonPosition(Texture [] textures, float scale){
        Vector2[] positions = new Vector2[textures.length];

        for(int  i = 0; i < positions.length; i++){
            positions[i] = new Vector2(Fin.HDWidth / 2 + 250, Fin.HDHeight * 0.4f);
        }
        return positions;
    }
    protected Button initializeBackButton(){
        Texture[] textures = new Texture[]{Textures.hexahonal, Textures.backToMainScreen};
        Vector2[] positions = initializeBackButtonPosition(textures, 2);
        return new Button(
                new CircleObject[]{
                        new CircleObject(textures[0], positions[0], 2,
                                Fin.backGroundButtonColor,0.0f),
                        new CircleObject(textures[1], positions[1], 1,
                                Fin.foreGroundButtonColor, 1)
                }
        );
    }
    protected Button initializeNextStageButton(){
        Texture [] textures = new Texture[]{Textures.hexahonal, Textures.continueButtonTexture};
        Vector2 [] positions = initializeNextStageButtonPosition(textures, 2);
        return new Button(
                new CircleObject[]{
                        new CircleObject(textures[0], positions[0], 2,
                                Fin.backGroundButtonColor,0.0f),
                        new CircleObject(textures[1], positions[1], 2,
                                Fin.foreGroundButtonColor, 1)
                }
        );
    }
    protected Map initializeMap(){
        Map map = new Map();
        CircleObject circleObject = new CircleObject(
                Textures.circle,
                new Vector2(Fin.WIDTH / 2,Fin.HEIGHT * 0.65f ),
                30,
                Fin.foreGroundButtonColor
        );
        map.addNewCharacter(
                new Character(
                        circleObject,
                        new HappyBall(circleObject.getPosition())
                )
                /**new PlanetDestroyer(
                 Textures.circle,
                 new Vector2(Fin.HDWidth / 2, Fin.HDWidth / 2),
                 400,
                 1,
                 Fin.foreGroundButtonColor
                 )**/
        );
        return map;
    }
    ///endregion

    @Override
    protected void eventListener(int i) {
        super.eventListener(i);
        switch (i){
            case BACK : gameStateManager.push(new MainScreen(gameStateManager)); break;
            case MUSIC : MusicRuler.switchMusic(); break;
            case SOUND : gameStateManager.push(new CongratulationsState(gameStateManager)); break;
            case NEXTSTAGE : gameStateManager.push(new GameState(gameStateManager)); break;
        }
    }

    @Override
    public void render() {
        super.render();
    }
}
