package com.sir.black.Tools.Special;

import com.badlogic.gdx.math.Vector2;

/**
 * Плавний переход від одного до іншого
 * 30.01.2018.
 */

public class Transition {
    //region fields
    /**
     * Число до якого рухаємовся
     */
    float target; // Число до якого рухаємовся
    /**
     * Поточне значення
     */
    float current; // Поточне значення
    /**
     * Крок з яким рухається обєкт
     */
    float step; // Крок з яким рухається обєкт

    float usualStep; // звичайний крок
    /**
     * Чи завжди рухатися до цілі?
     */
    boolean toTarget; //Чи завжди рухатися до цілі?
    // endregion
    //region construct
    /**
     * Пересуває поточне значення до потрібного
     * @param target Число до якого рухаємовся
     * @param current Поточне значення
     * @param step Крок з яким рухається обєкт
     * @param toTarget Чи завжди рухатися в правильному напрямку до цілі?
     */
    public Transition(float target, float current, float step, boolean toTarget) {
        this.target = target;
        this.current = current;
        this.usualStep = this.step = step;
        this.toTarget = toTarget;
    }

    /**
     * Пересуває поточне значення до потрібного, завжди рухатиметься в потрібному напрямку
     * @param target Число до якого рухаємовся
     * @param current Поточне значення
     * @param step Крок з яким рухається обєкт
     */
    public Transition(float target, float current, float step) {
        this(target, current, step, true);
     }

    /**
     * Пересуває поточне значення до потрібного числа з нуля, крок 1
     * @param target Число до якого рухаємося
     */
    public Transition(float target) {
        this(target, 0 , 1, true);
    }
    //endregion
    //region set/get
    public float getTarget() { return target; }
    public float getCurrent() { return current; }
    public float getStep() { return usualStep; }
    public boolean isToTarget() { return toTarget; }

    public void setTarget(float target) { this.target = target; }
    public void setCurrent(float current) { this.current = current; }
    public void setStep(float step) { this.usualStep = usualStep; }
    public void setToTarget(boolean toTarget) { this.toTarget = toTarget; }

    public void modTarget(float target) { this.target += target; }
    public void modCurrent(float current) { this.current += current; }
    public void modStep(float step) { this.usualStep += usualStep; }
    //endregion

    /**
     * Обновити стан обєтка
     */
    public float updateJumpSpin360(){
        return addJumpSpin360();
    }
    /**
     * Додати крок, для того щоб обєкт рухався до цілі
     */
    protected float addJumpSpin360(){
        step = 0;
        if (target != current) { // FIXME: 25.11.2018 change rotation, to slow spin
            step = target - current;
            current = target;
        }
        return step;
    }
    protected float rightDirectionSpin360() {
        if (current > 180 && target < 180) {
            return 1;
        } else if (current < 180 && target > 180) {
            return -1;
        } else
            return rightDirection();
    }
    protected void spin360(){
        if (current > 360) {
            current -= 360;
            target -= 360;
        }
        if (current < 0) {
            current += 360;
            target += 360;
        }
    }
    protected boolean isInsideSpin360(float number, float min, float max) {
        float minX = Math.min(min, max);
        float maxX = Math.max(min, max);
        if (minX <= number && number <= maxX)
            return true;
        else return false;
    }



    protected float addJump(){
        if (target == current) { return 0; } // Нічого не робить все і так вже зроблено
        else
        {
            step = 0;
            if (toTarget) {
                step = usualStep * rightDirection();
                float newCurrent = current + step;

                if (isInside(target, current, newCurrent)) {
                    step = target - current;
                    current = target;
                } else
                    current = newCurrent;
            }
            return step;
        }
    }
    /**
     * Визначити чи правильно рухається обєкт
     * @return +1 якщо правильно, -1 якщо не правильно
     */
    protected float rightDirection(){
        if ((target - current)/usualStep >= 0)
            return 1;
        else
            return -1;
    }
    /**
     * Чи знаходиться даний обєкт між двома іншими
     * @param number перевіряємий номер
     * @param min мінімальне значення
     * @param max максимальне значення
     * @return так, якщо знаходиться між ними і ні якщо не знаходиться між ними
     */
    protected boolean isInside(float number, float min, float max) {
        float minX = Math.min(min, max);
        float maxX = Math.max(min, max);
        if (minX <= number && number <= maxX)
        return true;
        else return false;
    }
}
