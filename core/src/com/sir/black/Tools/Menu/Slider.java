package com.sir.black.Tools.Menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Tools.Character.InitialObject.GameObject;

/**
 * Created by NoOne on 05.02.2018.
 */

public class Slider extends Button {
    //region fields
    /**
     * Ліва кнопка, права кнопка гортання і елемент який відображається між ними
     */
    protected GameObject left, right, middle; // Ліва кнопка, права кнопка гортання і елемент який відображається між ними
    /**
     * Варіанти які можуть бути вибрані
     */
    protected GameObject[] variant; // Варіанти які можуть бути вибрані
    //endregion

    //region construct
    /**
     * Варіантний вибір способом перебора вліво вправо по одному
     * @param number номер із списка
     * @param element загальний елемент - картинка
     * @param left взяти вліво
     * @param right взяти вправо
     * @param variant варіанти вибору
     */
    public Slider(int number, GameObject element, GameObject left, GameObject right, GameObject[] variant){
        super(element);
        this.left = left;
        this.right = right;
        this.variant = variant;
        setStatus(number);
    }

    /**
     * Варіантний вибір способом перебора вліво вправо по одному
     */
    public Slider() { this(0, null, null, null, null); }

    @Override
    protected void defaultValue() { super.defaultValue(); }
    //endregion

    //region get/set/switch
    public GameObject getLeft() { return left; }
    public GameObject getRight() { return right; }
    public GameObject getMiddle() { return middle; }
    public GameObject[] getVariant() { return variant; }

    public void setLeft(GameObject left) { this.left = left; }
    public void setRight(GameObject right) { this.right = right; }
    public void setVariant(GameObject[] variant) { this.variant = variant; }
    @Override
    public void setStatus(int status) {
        if (variant == null || variant.length == 0) {
            super.setStatus(status);
            middle = null;
        }
        else {
            //Перестраховка щоб попадав в діапазон
            super.setStatus(((status % this.variant.length) + this.variant.length) % this.variant.length);
            middle = variant[this.status];
        }
    }
    //endregion

    //region external
    @Override
    public int update(boolean goForth, Vector2 touch) {
        updateButton();
        changer(goForth, touch);
        return super.update(goForth, touch);
    }

    @Override
    public void trueDraw(SpriteBatch spriteBatch) {
        super.trueDraw(spriteBatch);
        if (right != null) right.trueDraw(spriteBatch);
        if (left != null) left.trueDraw(spriteBatch);
        if (middle != null) middle.trueDraw(spriteBatch);
    }
    //endregion

    //region internal
    /**
     * Обновить кнопку
     */
    public void updateButton() {
        if(right != null) right.update();
        if(left != null) left.update();
        if(middle != null) middle.update();
    }

    /**
     * Зміна статуса кнопки
     * @param goForth чи нажато
     * @param touch позиція нажаття
     */
    public void changer(boolean goForth, Vector2 touch) {
        if (right != null && right.inSide(goForth, touch)) goRight();
        if (left != null && left.inSide(goForth, touch)) goLeft();
    }

    /**
     * Крутнути на один вліво
     */
    public void goLeft() { setStatus(getStatus() + variant.length - 1); }

    /**
     * Крутнути на один вправо
     */
    public void goRight() { setStatus(getStatus() + 1); }
    //endregion
}
