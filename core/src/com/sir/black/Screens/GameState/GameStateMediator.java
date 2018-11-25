package com.sir.black.Screens.GameState;

import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Screens.Mediator.IHaveMediator;
import com.sir.black.Screens.Mediator.Mediator;
import com.sir.black.Screens.Maps.PlanetLocation;
import com.sir.black.Screens.SupportState.Camera2D;
import com.sir.black.Screens.SupportState.InputControl;
import com.sir.black.Screens.SupportState.Map;
import com.sir.black.Tools.Menu.Menu;
import com.sir.black.Tools.Special.SpecialMath;

public class GameStateMediator implements Mediator {
    //region field
    InputControl inputControl;
    Camera2D camera2D;
    Menu menu;
    Map map;
    //endregion

    //region construct
    public GameStateMediator(InputControl inputControl, Camera2D camera2D, Menu menu, Map map) {
        initialize(); // Значення за замовчуванням
        refreshExternalDependencies(); // Взято з архіва
        create(inputControl, camera2D, menu, map);
    }

    void initialize(){ }
    void refreshExternalDependencies(){ }
    void create(InputControl inputControl, Camera2D camera2D, Menu menu, Map map){
        this.inputControl = inputControl;
        this.camera2D = camera2D;
        this.menu = menu;
        this.map = map;

        if (inputControl != null) inputControl.setMediator(this);
        if (camera2D != null) camera2D.setMediator(this);
        if (menu != null) menu.setMediator(this);
        if (map != null) map.setMediator(this);
    }

    //endregion
    @Override
    public void notify(IHaveMediator sender) {
        if (sender instanceof InputControl) reactInputControl((InputControl) sender);
        if (sender instanceof Camera2D) reactCamera2D((Camera2D) sender);
        if (sender instanceof Menu) reactMenu((Menu) sender);
        if (sender instanceof PlanetLocation) reactMap((PlanetLocation) sender);
    }

    public void reactInputControl(InputControl inputControl) { // FIXME: 18.11.2018 spin ball and camera, not the Earth
        if (map instanceof PlanetLocation && inputControl.isTouched()) {
            float angle = calculateRotation(inputControl.getMousePositionRevert(), inputControl.getCoordinateDeltaVector());
            boolean isTouched = inputControl.isTouched();
            ((PlanetLocation) map).rotateBall(angle, isTouched);

            //camera2D.setRotationOfWorld(angle, ((PlanetLocation) map).getPlanetCenter());

            //((PlanetLocation) map).rotatePlanet(
            //        inputControl.getMousePositionRevert(),
            //        inputControl.getCoordinateDeltaVector());
            //camera2D.getCamera().rotate(1, /*Fin.planetCenter.x*/0, 0, 1);//.rotate(1);
        }
    }
    protected float calculateRotation(Vector2 mousePositionRevert, Vector2 coordinateDeltaVector){
        float angle;
        Vector2 firstVector = SpecialMath.subVector(mousePositionRevert, Fin.planetCenter);
        Vector2 secondVector = SpecialMath.subVector(firstVector, coordinateDeltaVector);
        angle = SpecialMath.angle(firstVector, secondVector);
        return angle;
    }

    public void reactMap(PlanetLocation planetLocation) {
        float angle = planetLocation.getSpinAngle();
        camera2D.setRotationOfWorld(angle,((PlanetLocation) map).getPlanetCenter());
    }

    public void reactCamera2D(Camera2D camera2D) {

    }

    public void reactMenu(Menu menu) {

    }
}
