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
    public GameObject elementMenu; // Активний образ елемента меню
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
    public Button(GameObject elementMenu) {
        archive();
        defaultValue();
        this.elementMenu = elementMenu;
    }

    public Button() {this(null); }

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
    public GameObject getElementMenu() { return elementMenu; }
    public boolean isMousePushButton() { return mousePushButton; }
    public int getStatus() { return status; }

    public void setElementMenu(GameObject elementMenu) { this.elementMenu = elementMenu; }
    public void setMousePushButton(boolean mousePushButton) { this.mousePushButton = mousePushButton; }
    public void setStatus(int status) { this.status = status; }

    //public void loadButtonPassive(GameObject go){ elementMenuPasive = go; elementMenu = go; }
    //public void loadButtonActive(GameObject go) { elementMenuActive = go; }
    //public void loadButtonPush(GameObject go) { elementMenuPush = go; }
    //endregion

    //region external
    public int update(boolean goForth, Vector2 touch) {
        int toReturn = -1;
        if (elementMenu != null && elementMenu.getPosition() != null) {
            elementMenu.update();
            if (elementMenu.inSide(goForth, touch)) {
//                        mouseInButton[i] = true; // Мишка попала на текстурку
//
//                        if (keyJump.KeyPress(goForth)) {
//                            activeFirstElement = i;
//                        } // Зявляється активний елемент
//
//                        if ((goForth) && (activeFirstElement == i)) {
//                            if (elementMenuActive[i]!=null)
//                                elementMenu[i] = elementMenuActive[i];
//                        } // При наведенні на елемент на якому було зажато мишку
//
//                        if (keyJump.KeyUp(goForth) && (activeFirstElement == i)) { // При відпусканні показує чи нажато на той елемент що був вибраний початково і вертає якщо так
                //mouseInButton = true;
                toReturn = 1;
//          }
            } else {
                //mouseInButton = false; // Мишка не на кнопці
                //if (elementMenuPasive != null)
                //    elementMenu = elementMenuPasive; // Колір вертає своє нормальне значення
            }
        }
        return toReturn;
    }

    public void trueDraw(SpriteBatch spriteBatch){
        if (elementMenu!=null) elementMenu.trueDraw(spriteBatch);
    }
    //endregion internal
}
