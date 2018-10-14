package com.sir.black.Tools.Menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Tools.GO.GameObject;
import com.sir.black.Tools.Special.KeyJump;

/**
 * Created by NoOne on 04.02.2018.
 */

public class Menu {
    //region static
    /**
     * Для визначення загального натискання
     */
    public static KeyJump keyJumpStatic; // Для визначення загального натискання
    /**
     * Вертає так, тільки тоді коли натиснуто в перший момент часу
     */
    public static boolean justTouch; // Вертає так, тільки тоді коли натиснуто в перший момент часу

    static {
        keyJumpStatic = new KeyJump();
        justTouch = false;
    }

    public static void checkGlobalTouch(boolean goForth){
        justTouch = keyJumpStatic.KeyPress(goForth);
    }
    //endregion

    //region fields
    /**
     * Зображення в меню
     */
    public GameObject[] pictures; // Зображення
    /**
     * Всі кнопки
     */
    public Button[] button; // Всі кнопки
    /**
     * Номер елемента із масива на якому було нажато і відпущено
     */
    protected int activeFirstElement; // Номер елемента із масива на якому було нажато і відпущено
    //endregion

    //region construct
    public Menu(int numberElementMenu) {
        button = new Button[numberElementMenu];
        archive();
        defaultValue();
    }

    protected void archive() { }

    protected void defaultValue() {
        activeFirstElement = -1;
        pictures = null;
    }
    //endregion

    //region get/set
    public GameObject[] getPictures() { return pictures; }
    public Button[] getButton() { return button; }

    public void setPictures(GameObject[] pictures) { this.pictures = pictures; }
    public void setButton(Button[] button) { this.button = button; }

    public void setPictures(GameObject pictures, int number) {
        if (number >= 0 && number < this.pictures.length)
            this.pictures[number] = pictures;
    }
    public void setButton(Button button, int number) {
        if (number>=0 && number<this.button.length)
        this.button[number] = button;
    }
    //endregion

    //region external
    /**
     * Обновлення меню
     * @param goForth чи нажато
     * @param touch позиція дотику
     * @return Номер на що було натиснуто
     */
    public int update(boolean goForth, Vector2 touch) {
        int toReturn = -1;
        if (justTouch) {
            for (int i = 0; i < button.length; i++) { // Всі кнопки перевіряємо
                if (button[i] != null) {
                    if (button[i].update(goForth, touch) == 1)
                        toReturn = i;
                }
            }
        }
        return toReturn;
    }

    /**
     * Отримати статус внутрішнього обєкта
     * @param elementNumber номер елемента
     * @return статус елемента, якщо такого елемента немає то вертає -100
     */
    public int getStatus(int elementNumber) {
        if (button == null) {
            return -100;
        }
        else {
            if (elementNumber >= 0 && elementNumber< button.length && button[elementNumber]!=null)
                return button[elementNumber].getStatus();
            else
                return -100;
        }
    }

    /**
     * Промальовка кнопок
     * @param spriteBatch спрайте бетч
     */
    public void draw(SpriteBatch spriteBatch) {
        if (button != null)
            for (int i = 0; i < button.length; i++) { // Промальовка елементів з якими можна взаємодіяти
                if (button[i] != null)
                    button[i].trueDraw(spriteBatch);
            }
        if (pictures != null) { // Промальовка картинок
            for (int i = 0; i < pictures.length; i++) {
                if (pictures != null)
                    pictures[i].draw();
            }
        }
    }
    //endregion
}
