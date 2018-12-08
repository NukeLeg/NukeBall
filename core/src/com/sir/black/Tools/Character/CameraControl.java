package com.sir.black.Tools.Character;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.InitialObject.GameObject;

/**
 * Контроль руху камери
 * 10.01.18
 */

public class CameraControl {
    //region fields
    /**
     * Позиція за якою наглядаємо, вона зазвичай сходна з позицію обєкта,
     * але якщо обєкт зникне, або розрушиться то камера остановиться на місці
     */
    Vector2 positionLooking; // Позиція за якою наглядаємо

    /**
     * Зовнішня камера
     */
    protected OrthographicCamera camera; // Камера
    /**
     * Обєкт за яким слідимо камерою
     */
    GameObject gameObject; // Обєкт за яким слідимо камерою

    /**
     * Висота ігрової області
     */
    float HEIGHT; // Висота ігрової області
    //endregion

    //region construct/refreshExternalDependencies/default
    /**
     * число яке визначає величину відношення радіусу кульки до розміру екрана в висоту (меншого із розмірів)
     */
    public CameraControl(OrthographicCamera camera, GameObject gameObject) {
        /// Вхідні параметри
        this.gameObject = gameObject; // Задаємо обєкт за яким будемо наглядати
        this.camera = camera;

        archive(); // Взято з архіва
        defaultValue(); // Значення за замовчуванням
    }

    /**
     * Взято з архіва
     */
    protected void archive() {
        this.HEIGHT = Fin.HEIGHT;
    }

    /**
     * Значення за замовчуванням
     */
    protected void defaultValue(){
        positionLooking = new Vector2();
        lookingPosition(); // Наглядаємо за обєктом
    }
    //endregion

    //region external
    /**
     * Слідкуємо за потрібним мячиком камерою
     */
    public void update() {
        lookingPosition(); // Позиція за якою слідкуємо
        setCameraPosition(); // Визначаємо позицію камери
        zoom(); // Визначаємо зум камери
        cameraUpdate(); // Обновляємо саму камеру
    }

    /**
     * Замінити обєт за яким спостерігаємо
     * @param gameObject обєкт спостереження
     */
    public void changeBall(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    /**
     * Замінити камеру
     * @param camera Нова камера
     */
    public void changeCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
    //endregion

    //region internal
    /**
     * Знаходимо позицію за якою будемо слідкувати
     */
    private void lookingPosition() {
        if (gameObject != null && gameObject.getPosition() != null) {
                positionLooking.x = gameObject.getPosition().x + gameObject.getSrcW() * gameObject.getScale().x / 2;
                positionLooking.y = gameObject.getPosition().y + gameObject.getSrcH() * gameObject.getScale().y / 2;
        }

    }

    /**
     * Центруємо камеру так, щоб вона дивилася за тим обєктом який потрібен
     */
    private void setCameraPosition() {
        camera.position.x = positionLooking.x; // Центруємо обєкт на координатах
        camera.position.y = positionLooking.y;
    }

    /**
     * Приводимо камеру до потрібного маштабу
     */
    private void zoom() {
        // Приводимо камеру в потрібну величину
        if (gameObject != null) {
            camera.zoom = moveToNumber(
                    camera.zoom,2 * gameObject.getCurrentSizeX() / HEIGHT);
        }
    }

    /**
     * Обновляємо позицію камери
     */
    private void cameraUpdate() {
        camera.update();
    }

    /**
     * Змінити розмір з поточного до потрібного значення
     * @param value значення яке зараз є
     * @param needNumber значення яке потрібне
     * @return повертає нове значення таке яке потрібне
     */
    private float moveToNumber(float value, float needNumber) {
        // Дописати
        value = needNumber;
        return value;
    }
    //endregion
}