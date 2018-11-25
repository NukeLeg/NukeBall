package com.sir.black.Tools.Menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Tools.Character.InitialObject.GameObject;

/**
 * Created by NoOne on 04.02.2018.
 */

public class Checkbox extends Button {
    //region fields
    /**
     * картинка яка символізує позитивне відмічення
     */
    protected GameObject checkSymbol; // картинка яка символізує позитивне відмічення
    /**
     * Чи відмічена кнопка чи ні
     */
    protected boolean isCheck; // Чи відмічена кнопка чи ні
    //endregion

    //region construct
    /**
     * Створити чекбокс
     * @param element Картинка чекбокса
     * @param checkSymbol символ який зявляється коли чекбокс відмічено
     * @param isCheck чи відмічено чи ні
     */
    public Checkbox(GameObject element, GameObject checkSymbol, boolean isCheck) {
        super(element);
        this.checkSymbol = checkSymbol;
        setCheck(isCheck);
    }

    /**
     * Тригер так/ні з незаданими кнопками і картинкою показника того чи натиснуто
     */
    public Checkbox() { this(null, null, false); }

    @Override
    protected void defaultValue() { super.defaultValue(); }
    //endregion

    //region get/set/switch
    public GameObject getCheckSymbol() { return checkSymbol; }
    public boolean isCheck() { return isCheck; }

    public void setCheckSymbol(GameObject checkSymbol) { this.checkSymbol = checkSymbol; }
    public void setCheck(boolean check) { isCheck = check; if (isCheck) status = 1; else status = 0; }

    @Override
    public void setStatus(int status) {
        super.setStatus(status);
        if (status == 0) isCheck = false;
        else  isCheck = true;
    }

    public void switchCheck() { setCheck(!isCheck()); }
    //endregion

    //region external
    @Override
    public int update(boolean goForth, Vector2 touch) {
        checker(goForth, touch);
        return super.update(goForth, touch);
    }

    @Override
    public void trueDraw(SpriteBatch spriteBatch) {
        super.trueDraw(spriteBatch);
        if (isCheck && checkSymbol != null) checkSymbol.trueDraw(spriteBatch);
    }
    //endregion

    //region internal
    /**
     * Перевірять натиснення
     * @param goForth натискання
     * @param touch точка натискання
     */
    public void checker(boolean goForth, Vector2 touch) {
        if (checkSymbol != null) checkSymbol.update();
        for (GameObject elementMenu : elementMenu){ if (elementMenu != null) elementMenu.update(); }
        for (GameObject elementMenu : elementMenu){
            if (elementMenu.inSide(goForth, touch)){
                switchCheck();
            }
        }
    }
    //endregion
}
