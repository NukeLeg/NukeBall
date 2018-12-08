package com.sir.black.Screens.SupportState;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sir.black.Data.Fin;
import com.sir.black.Screens.Mediator.IHaveMediator;
import com.sir.black.Screens.Mediator.Mediator;
import com.sir.black.Tools.Special.Checker;
import com.sir.black.Tools.Special.Transition;

/**
 * Created by NoOne on 21.06.2018.
 */

public class Camera2D implements IHaveMediator {
    //region fields
    protected Mediator mediator;
    /**
     * Камера
     */
    protected OrthographicCamera camera; // Камера
    /**
     * Позиція дотику
     */
    protected Vector3 touchPos; // Позиція дотику

    protected Transition rotationAngle;

    protected Vector2 pointOfSpinning;
    //endregion

    //region construct
    public Camera2D(int WIDTH, int HEIGHT){
        this.camera = new OrthographicCamera(); /**Ортографічна камера для смогї гри*/
        this.camera.setToOrtho(false, WIDTH, HEIGHT); /** встановлюємо видове вікно та вирівнюємо по центру*/
        this.touchPos = new Vector3(); /**Зануляємо координати дотику */
        this.rotationAngle = new Transition(0, 0, 20);
        this.pointOfSpinning = new Vector2();
    }
    //endregion

    //region get/set
    public OrthographicCamera getCamera() { return camera; }
    public Vector3 getTouchPos() { return touchPos; }
    public Vector2 getTouchPosV2() { return new Vector2(touchPos.x, touchPos.y); }
    public Mediator getMediator() { return mediator; }

    public void setMediator(Mediator mediator) { this.mediator = mediator; }

    /**
     * Для будь якого меню - розтянути(стиснути) і змістити камеру так,
     * щоб задані розміри екрану HDWidth, HDHeight були повнісю на екрані
     * грубо кажучи - екран який задаємо в параметрах перемаштабується так,
     * щоб його повністю було видно на девайесі не залежно від того яке розширення девайса
     */
    public void setCameraMenu(float HDwidth, float HDheight, float width, float height) {
        if (width != 0) { // Перевірка на пуль
            camera.zoom = HDwidth / width; // Маштабуємо
            camera.position.x += (HDwidth - width) / 2; // Зміщуємо координату Х
            camera.position.y += (HDheight - height) / 2; // Зміщуємо координату Y
        }
    }

    public void setTouchPos(float x, float y){
        touchPos.set(x, y, 0);
        camera.unproject(touchPos);
    }
    //endregion

    public void setRotationOfWorld(float angle, Vector2 pointOfSpinning){
        if (pointOfSpinning != null) {
            rotationAngle.setTarget(angle);
            this.pointOfSpinning = new Vector2(pointOfSpinning);
            rotationAngleUpdate();
        }
    }
    public void setZoom(int numberOfLayersCurrent){
        //getCamera().zoom = 1 + numberOfLayersCurrent * 0.0625f;
    }

    public void update(){
        camera.update();
        mediatorUpdate();
    }
    protected void rotationAngleUpdate(){
        float angle = rotationAngle.updateJumpSpin360();
        getCamera().rotateAround(
                new Vector3(pointOfSpinning.x, pointOfSpinning.y, 0),
                new Vector3(0, 0, 1), angle);
    }

    protected void mediatorUpdate(){
        if (mediator != null)
            mediator.notify(this);
    }
}
