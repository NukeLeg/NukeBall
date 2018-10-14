package com.sir.black.Tools.Menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Tools.Character.GameObject.GameObject;

/**
 * Created by NoOne on 05.02.2018.
 */

public class Switch extends Button {
    //region fields
    /**
     * картинка яка символізує відмічення
     */
    protected GameObject checkSymbol; // картинка яка символізує відмічення
    /**
     * Дефолтний чекер
     */
    protected GameObject defaultCheck; // Дефолтний чекер
    /**
     * Альтернативні варіанти вибору, картинки вибору
     */
    protected GameObject[] alternant; // Альтернативні варіанти вибору, картинки вибору
    /**
     * Картинки чекерів
     */
    protected GameObject[] checkAll; // Всі картинки чекерів
    //endregion

    //region construct
    /**
     * Світч на кілька позицій
     * @param status статус альтернатив
     * @param element Картинка чекбокса
     * @param checkSymbol символ який зявляється коли чекбокс відмічено(дефолтний чекер)
     * @param alternant можливі альтернативи
     * @param checkAll чекери на кожен окремий випадок
     */
    public Switch(int status, GameObject element, GameObject checkSymbol, GameObject[] alternant, GameObject[] checkAll) {
        super(element);
        this.status = status;
        this.defaultCheck = checkSymbol;
        this.checkSymbol = checkSymbol;
        this.alternant = alternant;
        this.checkAll = checkAll;
    }

    /**
     * Тригер так/ні з незаданими кнопками і картинкою показника того чи натиснуто
     */
    public Switch() { this(-1, null, null, null, null); }

    @Override
    protected void defaultValue() { super.defaultValue(); }
    //endregion

    //region get/set/switch
    public GameObject getCheckSymbol() { return checkSymbol; }
    public GameObject getDefaultCheck() { return defaultCheck; }
    public GameObject[] getAlternant() { return alternant; }
    public GameObject[] getCheckAll() { return checkAll; }

    public void setAlternant(GameObject[] alternant) { this.alternant = alternant; }
    public void setCheckAll(GameObject[] checkAll) { this.checkAll = checkAll; }
    public void setDefaultCheck(GameObject defaultCheck) { this.defaultCheck = defaultCheck; }

    @Override
    public void setStatus(int status) {
        super.setStatus(status);
        if (status == -1) {
            checkSymbol = defaultCheck; // Якщо нічого не вибрано то дефолтний чекер
        }
        else {
            if (status >= 0 && status < checkAll.length)
                checkSymbol = checkAll[status]; // Якщо вибрано в діапазоні інснуючих то брати із них
            else
                checkSymbol = null;
        }
    }

    //endregion

    //region external
    @Override
    public int update(boolean goForth, Vector2 touch) {
        checkerUpdate();
        swithcerUpdate();
        switcher(goForth, touch);
        return super.update(goForth, touch);
    }

    @Override
    public void trueDraw(SpriteBatch spriteBatch) {
        super.trueDraw(spriteBatch); // Спочатку задня картинка

        if (alternant != null) { // Потім всі кнопки
            for (int i = 0; i < alternant.length; i++) {
                if (alternant[i] != null) alternant[i].trueDraw(spriteBatch);
            }
        }
        // Потім картинка яка відмічає
        if (checkSymbol != null) checkSymbol.trueDraw(spriteBatch);
    }
    //endregion

    //region internal
    /**
     * Обновлення всіх свічерів
     */
    protected void swithcerUpdate(){
        if (alternant != null) {
            for (int i = 0; i < alternant.length; i++) {
                if (alternant[i] != null) {
                    alternant[i].update();
                }
            }
        }
    }

    /**
     * Обновлення чекера
     */
    protected void checkerUpdate(){
        if (checkSymbol != null) checkSymbol.update();
    }

    /**
     * Перемикач між можливими станами
     * @param goForth чи нажато
     * @param touch точка дотику
     */
    protected void switcher(boolean goForth, Vector2 touch){
        if (alternant != null) {
            for (int i = 0; i < alternant.length; i++) {
                if (alternant[i].inSide(goForth, touch)) {
                    setStatus(i);
                }
            }
        }
    }
    //endregion
}
