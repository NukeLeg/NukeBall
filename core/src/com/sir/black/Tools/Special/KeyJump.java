package com.sir.black.Tools.Special;

/**
 * Клас просунутих натискань
 * 04.01.18
 */

public class KeyJump {
    //region fields
    /**
     * Для методу KeyPress
     */
    boolean put; // Для методу KeyPress
    /**
     * Для методу KeyUp
     */
    boolean put2; // Для методу KeyUp
    /**
     * Для методу KeyStay
     */
    int put0; // Для методу KeyStay
    /**
     * Для методу KeyUsualy
     */
    int usualFunk; // Для методу KeyUsualy
    //endregion

    //region external
    /**
     * В момент натиснення вертає так, подальше тримання не грає ролі
     * @param press Нажаття
     * @return Так при моменті натискання, інакше ні
     */
    public boolean KeyPress(boolean press) {
        if (!press) { put = true; }
        if (press != put) return false;
        else
        {
            put = false;
            return true;
        }
        //region saveCode
        /*if (press)
        {
            if (put) { put = false; return true; }
            else return false;
        }
        else
        {
            put = true;
            return false;
        }*/
        //endregion
    }

    /**
     * В момент відпускання вертає так, тримання до того не грає ролі
     * @param press Нажаття
     * @return Так при моменті відпускання, інакше ні
     */
    public boolean KeyUp(boolean press) {
        if (press) put2 = true;
        if (press == put2) return false;
        else {
            put2 = false;
            return true;
        }
    }

    /**
     * Вертає скільки часу трималося кнопка
     * @param press Нажаття
     * @return Скільки часу підряд трималася кнопка
     */
    public int KeyStay(boolean press) {
        if (press) {
            put0++;
            return put0;
        } else {
            put0 = 0;
            return 0;
        }
    }

    /**
     * Вертає так, як у віндовсіній менюшці, нажавши вниз - раз гортає вниз, чекає трохи і потім бистро
     * @param press Власне сам натиск
     * @param timeWait Час затримки до швидкого руху
     * @param slowly Час заповільнення
     * @return Вертає тру як у віндовс меню для гортання
     */
    public boolean KeyUsualy(boolean press, byte timeWait, byte slowly) {
        if (press) {
            usualFunk++;
            if (usualFunk == 1) return true;
            else {
                if (timeWait > usualFunk) return false;
                else return usualFunk % (slowly + 1) == 0;
            }
        } else {
            usualFunk = 0;
            return false;
        }
    }
    //endregion
}
