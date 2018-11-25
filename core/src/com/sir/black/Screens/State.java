package com.sir.black.Screens;

import com.sir.black.Common.GameStateManager;
import com.sir.black.Screens.GameState.GameState;
import com.sir.black.Screens.Mediator.Mediator;
import com.sir.black.Screens.SupportState.Camera2D;
import com.sir.black.Screens.SupportState.DrawTools;
import com.sir.black.Screens.SupportState.InputControl;
import com.sir.black.Screens.SupportState.LocalCounter;
import com.sir.black.Screens.SupportState.Map;
import com.sir.black.Screens.SupportState.ScreenParams;
import com.sir.black.Tools.Menu.Menu;

/**
 * Стан гри
 * 02.01.18
 */

public class State {
    //region field
    protected GameStateManager gameStateManager; // посилання на менеджер станів гри для керування вікнами
    protected Mediator mediator; // is ruler others classes

    protected boolean pause;

    protected ScreenParams screenParams; // Параметри екрана
    protected LocalCounter localCounter; // Лічильник
    protected DrawTools drawTools; // Для промальовки всього
    protected InputControl inputControl; // Все що вводиться ззовні іде сюди

    protected Menu menu; // Менюшка всередині гри
    protected Map map; // Розміщення всіх обєктів 2D

    protected Camera2D camera2D; // камера загальна для 2Д
    protected Camera2D camera2DMenu; // камера меню
    //endregion

    //region construct/refreshExternalDependencies/default
    /**
     * Створюємо нове меню
     * @param gameStateManager Менеджер який керує переходами між меню
     */
    public State(GameStateManager gameStateManager){
        // Вхідні параметри
        initialize(); // Дефолтні значення
        refreshExternalDependencies(); // З архіва
        create(gameStateManager);
        firstAction();
    }

    /**
     * Значення за замовчуванням
     */
    protected void initialize() {
        screenParams = new ScreenParams();
        localCounter = new LocalCounter();
        map = new Map();
        pause = false;
        drawTools = new DrawTools();
        inputControl = new InputControl();
        camera2D = new Camera2D(screenParams.getWIDTH(), screenParams.getHEIGHT());
        camera2DMenu = new Camera2D(screenParams.getWIDTH(), screenParams.getHEIGHT());
        camera2DMenu.setCameraMenu(screenParams.getHDWidth(), screenParams.getHDHeight(), screenParams.getWIDTH(), screenParams.getHEIGHT());
        gameStateManager = null;
    }

    /**
     * Параметри взяті з інших зовнішніх джерел
     */
    protected void refreshExternalDependencies() { }

    protected void create(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
    }

    protected void firstAction(){
        cameraUpdate(); /**Обновлення камери гри*/
        cameraMenuUpdate(); /**Обновлення камери меню*/
    }
    //endregion

    //region external
    /**
     * оновлення стану через певні проміжки часу
     * */
    ///reagion update
    public void update() {
        if (!pause) {
            mapUpdate(); /**Обновлення обєктів 2D*/
            handleInput2D(); /** Зробити камері залежно від вводу даних*/
            inputControlUpdate(); /** Обновлення введених даних*/
            screenParamsUpdate(); /** Обновлення парметрів екрана*/
            localCounterUpdate(); /** Плюсування лічильника*/
            menuUpdate(); /** Відредагувати на те що відбувається в меню*/
            drawToolsUpdate(); // Обновлення засобів для промальовки
            cameraUpdate(); /**Обновлення камери гри*/
            cameraMenuUpdate(); /**Обновлення камери меню*/
        }
    }
    /**
     * Відредагувати на те що відбувається в меню
     * На яку кнопку було натиснуто
     */
    protected void menuUpdate() {
        Menu.checkGlobalTouch(inputControl.isTouched()); // Перевірка першого нажимання
        if (menu != null) { // Якщо меню існує то зробити відповідні дії
            int numberButton = menu.update(inputControl.isTouched(), camera2DMenu.getTouchPosV2());
            eventListener(numberButton);
        }
    }

    protected void screenParamsUpdate(){
        if (screenParams!=null) screenParams.update();
    }

    protected void inputControlUpdate() {
        if (inputControl != null) inputControl.update();
    }
    /**
     * Лічильник цього стану
     */
    protected void localCounterUpdate() { if (localCounter != null) localCounter.update(); } /**Лічильник для цього конкретного стану */
    /**
     * Обновлення камери гри
     */
    protected void cameraUpdate() { if (camera2D!=null) camera2D.update(); } /**Обновлення камери гри*/
    /**
     * Обновлення камери меню
     */
    protected void cameraMenuUpdate() { if (camera2DMenu!=null) camera2DMenu.update(); } /**Обновлення камери меню*/

    protected void drawToolsUpdate(){
        if (drawTools!=null) drawTools.update();
    }

    protected void mapUpdate() { if (map != null) map.update(); }

    public void pauseOn(){ pause = true;}
    public void pauseOff(){pause = false;}
    /// endregion

    /**
     * промальвка стану
     * */
    public void render() {
        drawToolsDraw();
    }
    //endregion

    //region internal
    /**
     * обробник користувацького вводу
     * */
    protected void handleInput2D() {
        if (camera2D != null) camera2D.setTouchPos(inputControl.getX(), inputControl.getY());
        if (camera2DMenu != null) camera2DMenu.setTouchPos(inputControl.getX(), inputControl.getY());
    }


    /**
     * Події що відбуваються в меню
     * @param i Номер нажатої кнопки
     * 0 - start button
     */
    protected void eventListener(int i) {

    }


    /**
     * Тут лише додається те що має промальовуватся через layerRuler
     */
    protected void drawToolsDraw() {
        drawTools.draw(map, camera2D);
        drawTools.draw(menu, camera2DMenu);
    }
    //endregion
}