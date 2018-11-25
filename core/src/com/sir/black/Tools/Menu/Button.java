package com.sir.black.Tools.Menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Tools.Character.InitialObject.GameObject;

/**
 * Created by NoOne on 04.02.2018.
 */

public class Button {
    //region fields
    /**
     * Активний образ елемента меню
     */
    public GameObject [] elementMenu; // Активний образ елемента меню
    /**
     * Мишка нажала кнопку
     */
    public boolean mousePushButton; // Мишка нажала кнопку
    /**
     * Стан нажатої кнопки
     */
    public int status; // Стан нажатої кнопки
    //endregion

    //region construct
    public Button(GameObject [] elementMenu) {
        archive();
        defaultValue();
        this.elementMenu = elementMenu;
    }
    public Button(GameObject elementMenu) {
        archive();
        defaultValue();
        setElementMenu(elementMenu);
    }

    protected void archive() { }

    protected void defaultValue() {
        //this.mouseInButton = false;
        this.mousePushButton = false;
        this.elementMenu = null;
        //elementMenuPasive = null;
        //elementMenuActive = null;
        //elementMenuPush = null;
        this.status = -1;
    }
    //endregion

    //region get/set
    public GameObject [] getElementMenu() { return elementMenu; }
    public boolean isMousePushButton() { return mousePushButton; }
    public int getStatus() { return status; }

    public void setElementMenu(GameObject [] elementMenu) { this.elementMenu = elementMenu; }
    public void setElementMenu(GameObject  elementMenu) {
        this.elementMenu = new GameObject[1];
        this.elementMenu[0] = elementMenu;
    }
    public void setMousePushButton(boolean mousePushButton) { this.mousePushButton = mousePushButton; }
    public void setStatus(int status) { this.status = status; }

    //endregion

    //region external
    public int update(boolean goForth, Vector2 touch) {
        int toReturn = -1;
        for(GameObject elementMenu : elementMenu)
        if (elementMenu != null && elementMenu.getPosition() != null) {
            elementMenu.update();
            if (elementMenu.inSide(goForth, touch)) {
                toReturn = 1;
            } else {
            }
        }
        return toReturn;
    }

    public void trueDraw(SpriteBatch spriteBatch){
        for(GameObject elementMenu : elementMenu){
            if (elementMenu!=null) elementMenu.trueDraw(spriteBatch);
        }
    }
    //endregion internal
}
