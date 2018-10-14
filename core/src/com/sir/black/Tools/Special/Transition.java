package com.sir.black.Tools.Special;

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
        this.step = step;
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
    public float getStep() { return step; }
    public boolean isToTarget() { return toTarget; }

    public void setTarget(float target) { this.target = target; }
    public void setCurrent(float current) { this.current = current; }
    public void setStep(float step) { this.step = step; }
    public void setToTarget(boolean toTarget) { this.toTarget = toTarget; }

    public void modTarget(float target) { this.target += target; }
    public void modCurrent(float current) { this.current += current; }
    public void modStep(float step) { this.step += step; }
    //endregion

    //region external
    /**
     * Обновити стан обєтка
     */
    public void update() {
        add();
    }

    /**
     * Визначити чи правильно рухається обєкт
     * @return +1 якщо правильно, -1 якщо не правильно
     */
    public float rightDirection(){
        if ((target - current)/step >= 0) return 1;
        else return -1;
    }
    //endregion

    //region internal
    /**
     * Додати крок, для того щоб обєкт рухався до цілі
     */
    protected void add(){
        if (target == current) {  } // Нічого не робить все і так вже зроблено
        else
        {
            if (toTarget) step = step * rightDirection();
            float newCurrent = current + step;

            if (isInside(target, current, newCurrent)) current = target;
            else current = newCurrent;
        }
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
    //endregion
}
