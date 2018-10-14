package com.sir.black.Screens.SupportState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by NoOne on 21.06.2018.
 */

public class InputControl {
    //region field
    /**
     * Зовнішній ввод даних
     */
    protected Input input; // Зовнішній ввод даних

    /**
     * Для того щоб витягнути кручення колесика
     */
    Inp inp; // Для того щоб витягнути кручення колесика

    /**
     * поточне положення курсору на екрані по вісі X не нормована
     */
    protected int x; //поточне положення курсору на екрані по вісі X
    /**
     * поточне положення курсору на екрані по вісі Y не нормована
     */
    protected int y; //поточне положення курсору на екрані по вісі Y.
    /**
     * Зміна по x
     */
    protected int dx; // Зміна по x
    /**
     * Зміна по y
     */
    protected int dy; // Зміна по y

    /**
     * Ліва кнопка
     */
    protected boolean leftMouse; // Ліва кнопка
    /**
     * Права кнопка
     */
    protected boolean rightMouse; // Права кнопка
    /**
     * Середня кнопка
     */
    protected boolean middleMouse; // Середня кнопка

    /**
     * Колесце миші
     */
    protected int mouseWheel; // Колесце миші

    /**
     * вихід
     */
    protected boolean esc; // Виход
    /**
     * ввід
     */
    protected boolean enter; // ввід
    /**
     * пробіл
     */
    protected boolean space; // пробіл
    protected boolean backSpace; //
    protected boolean delete;

    /**
     * кнопка яка відповідає за знак множення
     */
    protected boolean star; //кнопка яка відповідає за знак множення
    /**
     * кнопка яка відповідає за знак слеш
     */
    protected boolean slash; //кнопка яка відповідає за знак слеш
    /**
     * кнопка яка відповідає за крапку
     */
    protected boolean dot; //кнопка яка відповідає за крапку

    /**
     * Вліво
     */
    protected boolean left; // Вліво
    /**
     * Вправо
     */
    protected boolean right; // Вправо
    /**
     * ВВерх
     */
    protected boolean up; // ВВерх
    /**
     * Вниз
     */
    protected boolean down; // Вниз

    /**
     * кнопка А
     */
    protected boolean A; //кнопка А
    /**
     * кнопка В
     */
    protected boolean B; //кнопка В
    /**
     * кнопка С
     */
    protected boolean C; //кнопка С
    /**
     * кнопка D
     */
    protected boolean D; //кнопка D

    /**
     * numPad цифри
     */
    protected boolean[] num; //numPad цифри

    /**
     * Скоросне натиснення на кнопку (час натиснення менший за counter)
     */
    protected boolean isFastClick; //Скоросне натиснення на кнопку (час натиснення менший за counter)
    /**
     * Скільки часу натиснуто на кнопку
     */
    protected int counter; //Скільки часу натиснуто на кнопку
    /**
     * Максимальний час натиснення
     */
    protected int counterMax; //Максимальний час натиснення
    //endregion

    //region get/set
    public Vector2 getMousePosition() { return new Vector2(x,y); }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getDx() { return dx; }
    public int getDy() { return dy; }

    public boolean isLeftMouse() { return leftMouse; }
    public boolean isRightMouse() { return rightMouse; }
    public boolean isMiddleMouse() { return middleMouse; }
    public int getMouseWheel() { return mouseWheel;  }

    public boolean isEsc() { return esc; }
    public boolean isEnter() { return enter; }
    public boolean isSpace() { return space; }
    public boolean isBackSpace() { return backSpace; }
    public boolean isDelete() { return delete; }

    public boolean isStar() { return star; }
    public boolean isSlash() { return slash; }
    public boolean isDot() { return dot; }

    public boolean[] getNum() { return num; }

    public boolean isLeft() { return left; }
    public boolean isRight() { return right; }
    public boolean isUp() { return up; }
    public boolean isDown() { return down; }

    public boolean isA() { return A; }
    public boolean isB() { return B; }
    public boolean isC() { return C; }
    public boolean isD() { return D; }

    /**
     * покаазує чи щось було натиснуто на клавіатурі або миші
     * @return true якщо було натиснення
     */
    public boolean isAnyKeyboard(){
        boolean active = false;
        active = isLeft() || isRight() || isUp() || isDown()
                // ||  isA() || isB() || isC() || isD()
                || isEnter() || isSpace() || isBackSpace() || isDelete() || isEsc()
                || isStar() || isSlash() || isDot();
        for (int i = 0; i < getNum().length; i++) {
            active |= getNum()[i];
        }
        return active;
    }
    /**
     * показує чи були зміни стану миші або клавіатури
     * @return true - якщо перемістили мишу або щось натиснули і відпустили
     */
    public boolean isAnyChanges(){
        boolean active = false;
        active = isAnyKeyboard() ||
                middleMouse || leftMouse || rightMouse ||
                dx != 0 || dy != 0 || mouseWheel != 0;
        return active;
    }

    public boolean isFastClick() { return isFastClick; }
    public boolean isTouched(){ return isLeftMouse(); }


    public void setCounterMax(int counterMax) { this.counterMax = counterMax; }
    //endregion

    //region construct
    public InputControl(){
        defaultValue();
        update();
    }
    protected void defaultValue(){
        num = new boolean[10];
        input = Gdx.input; /**Зовнішній ввод*/
        inp = new Inp();
        input.setInputProcessor(inp);

        isFastClick = false;
        counter = 0;
        counterMax = 20;
    }
    //endregion

    //region internal

    /**
     * оновлення стану миші
     */
    protected void updateMouse(){
        x = input.getX();
        y = input.getY();
        dx = input.getDeltaX();
        dy = input.getDeltaY();

        leftMouse = input.isButtonPressed(Input.Buttons.LEFT);
        rightMouse = input.isButtonPressed(Input.Buttons.RIGHT);
        middleMouse = input.isButtonPressed(Input.Buttons.MIDDLE);
    }

    /**
     * оновлення стану колесця миші
     */
    protected void updateWheel(){
        input.setInputProcessor(inp);
        mouseWheel = inp.amount;
        inp.update();
    }

    /**
     * оновлення стану стрілочок на клавіатурі
     */
    protected void updateArrow(){
        left = input.isKeyPressed(Input.Keys.LEFT);
        right= input.isKeyPressed(Input.Keys.RIGHT);
        up   = input.isKeyPressed(Input.Keys.UP);
        down = input.isKeyPressed(Input.Keys.DOWN);
    }

    protected void updateComand(){

    }

    /**
     * оновлення стану кнопок типу DELETE, SPACE, *
     */
    protected void updateSymbol(){
        esc  = input.isKeyPressed(Input.Keys.ESCAPE);
        enter= input.isKeyPressed(Input.Keys.ENTER);
        space= input.isKeyPressed(Input.Keys.SPACE);
        backSpace= input.isKeyPressed(Input.Keys.BACKSPACE);
        delete = input.isKeyPressed(Input.Keys.FORWARD_DEL);
        star = input.isKeyPressed(Input.Keys.STAR);
        slash = input.isKeyPressed(Input.Keys.SLASH);
        dot = input.isKeyPressed(Input.Keys.PERIOD);
    }

    /**
     * оновлення стану символьних кнопок A,B,C,D
     */
    protected void updateLetter(){
        A = input.isKeyPressed(Input.Keys.A);
        B = input.isKeyPressed(Input.Keys.B);
        C = input.isKeyPressed(Input.Keys.C);
        D = input.isKeyPressed(Input.Keys.D);
    }

    /**
     * оновлення цифрових кнопок NumPad
     */
    protected void updateNum() {
        for (int i = 0; i < num.length; i++) {
            num[i] = input.isKeyPressed(Input.Keys.NUM_0 + i)
                    || input.isKeyPressed(Input.Keys.NUMPAD_0 + i)
            ;
        }
    }

    /**
     * оновлення FastClick
     */
    protected void updateClicker(){
        if (counter < counterMax) isFastClick = true;
        else isFastClick = false;

        counter++;
        if (isTouched()) {
            counter = 0;
        }
    }
    //endregion

    //region external
    public void update() {
        updateMouse();
        updateWheel();
        updateArrow();
        updateComand();
        updateSymbol();
        updateLetter();
        updateNum();
        updateClicker();
    }

    //endregion

    /**
     * кастомний InputProcessor для того щоб можна було обробляти вхідні дані як нам схочеться
     */
    class Inp implements InputProcessor{
        int amount = 0;
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            this.amount = amount;
            return false;
        }
        public void update(){
            amount = 0;
        }
    }
}
