package com.sir.black.Tools.Special;

import com.badlogic.gdx.graphics.Color;
import com.sir.black.Data.Fin;

import java.util.Random;

/**
 * Клас рандомних функцій
 * 05.01.18
 */

public class ZeroFun {
    //region fields
    // Спеціальні змінні для метода Рандомних чисел
    // Для метода франк змінні
    static long af = 16807, mf = 2147483647, qf = 127773, r = 2836, hi, lo;
    static long af2 = 16807, mf2 = 2147483647, qf2 = 127773, r2 = 2836;
    static float iSeed = 1001 + new Random().nextInt(100000);
    //endregion

    //region external
    /**
     * Генерація випадкових чисел від 0 до 1 виключно
     * @return Число в межах від 0 до 1 виключно
     */
    public static float frand() {
        af2 = af + Fin.counter % 257;
        mf2 = mf - Fin.counter % 4261;
        qf2 = qf - Fin.counter % 743;
        r2 = r + Fin.counter % 127;
        hi = (long)(iSeed / qf2);
        lo = (long)(iSeed - qf2 * hi);
        iSeed = (int)(af2 * lo - r2 * hi);
        if (iSeed <= 0) iSeed = (int)(iSeed + mf);
        //if (iSeed <= 0) iSeed = (int)(-iSeed);
        return iSeed / ((float) Integer.MAX_VALUE + 1);
    }

    /**
     * Видає випадкове число від 0 до заданого
     * @param maxNumber максимальне число
     * @return випадкове число від 0 до числа в параметрах виключно
     */
    public static float frand(float maxNumber) {
        return frand() * maxNumber;
    }

    /**
     * Видає випадкове число в заданому діапазоні
     * @param min мінімальне число
     * @param max максимальне число
     * @return випадкове від мінімального до максимальне
     */
    public static float frand(float min, float max) {
        return frand() * (max - min) + min;
    }

    /**
     * Видає випадкове в межах від мінус даного параметра до плюс даного параметра
     * крім того числа більш кучніше лягають ближче до нуля
     * @param number максимальне число
     * @return число в межах -параметр до +параметр
     */
    public static float middle(float number) {
        return frand() * ((frand() - 0.5f) * 2) * number;
    }

    /**
     * Випадкові числа від нуля до максимального виключно (видає ціле значення)
     * @param maxNumber максимальне значення більше якого бути не може
     * @return ціле число в діапазоні від нуля до заданого
     */
    public static int frandInt(int maxNumber) {
        return (int)(frand((float) maxNumber));
    }

    /**
     * Видає ціле число в заданому діапазоні
     * @param min мінімальне число
     * @param max максимальне
     * @return число від мінімального до максимального виключно
     */
    public static int frandInt(int min, int max) {
        return (int)(frand((float)min, max));
    }

    /**
     * Ймовірність події
     * Вводити від 0 до 1
     * Чим більша ймовірність тим більша ймовірність що поверне true
     * Наприклад
     * 0,2 20%;
     * 0,7 70%;
     * -0,3 0%;
     * 0 0%;
     * -10 0%;
     * 1 100%;
     * 3,2 100%
     * @param part число ймовірності
     * @return сталася подія чи ні
     */
    public static boolean posibility(float part) {
        if (part>frand()) return true;
        else return false;
    }

    /**
     * Ймовірність випадання 50%
     * @return вертає ймовірні 50 на 50 тру і фолс
     */
    public static boolean posibility() {
        return posibility(0.5f);
    }

    /**
     * Число плюс 1 або мінус один з заданою ймовіністю
     * чим більше число тим йомірніше що дасть одиничку а не -1
     * @param part число від 0 до 1
     * @return +1 або -1
     */
    public static float plusMinus(float part) {
        if (part>frand()) return 1;
        else return -1;
    }

    /**
     * Число плюс 1 або мінус один з ймовіністю 50 на 50
     * @return +1 або -1 з йомвірністю 50 на 50
     */
    public static float plusMinus() {
        return plusMinus(0.5f);
    }

    /**
     * Число плюс 1 або 0 з заданою ймовіністю
     * чим більше число тим йомірніше що дасть одиничку а не -1
     * @param part число від 0 до 1
     * @return +1 або 0
     */
    public static float plusZero(float part) {
        if (part>frand()) return 1;
        else return 0;
    }

    /**
     * Число плюс 1 або 0 з ймовіністю 50 на 50
     * @return +1 або 0 з йомвірністю 50 на 50
     */
    public static float plusZero() {
        return plusZero(0.5f);
    }

    /**
     * Конвертує Булівську змінну в 1 або 0
     * @param value булівське так чи ні
     * @return якщо булівське так то вертає 1, якщо ні то вертає 0
     */
    public static int booleanToInt(boolean value) {
        if (value) return 1;
        else return 0;
    }

    /**
     * Конвертує Булівську змінну в 1 або -1
     * @param value булівське так чи ні
     * @return якщо булівське так то вертає 1, якщо ні то вертає -1
     */
    public static int booleanToIntSign(boolean value) {
        if (value) return 1;
        else return -1;
    }

    /**
     * Дати випадковий колір(випадкова альфа також)
     * @return випадковий колір, всі 4 значення
     */
    public static Color frandColor(){
        Color color = new Color(frand(), frand(), frand(), frand());
        return color;
    }

    /**
     * Набір спеціальних кольорів
     * @param color номер кольору від 0-16 включно
     * @return повертає один із стандартних заданих кольорів
     */
    public static Color specialColor(int color){
        switch (color) {
            case 0: return new Color(238/256f,28/256f,37/256f,256f/256f);
            case 1: return new Color(37/256f,68/256f,159/256f,256f/256f);
            case 2: return new Color(1/256f,165/256f,96/256f,256f/256f);
            case 3: return new Color(254/256f,241/256f,1/256f,256f/256f);
            case 4: return new Color(166/256f,34/256f,144/256f,256f/256f);
            case 5: return new Color(1/256f,177/256f,177/256f,256f/256f);
            case 6: return new Color(245/256f,170/256f,29/256f,256f/256f);
            case 7: return new Color(238/256f,135/256f,239/256f,256f/256f);
            case 8: return new Color(157/256f,192/256f,70/256f,256f/256f);
            case 9: return new Color(122/256f,115/256f,89/256f,256f/256f);
            case 10: return new Color(168/256f,37/256f,78/256f,256f/256f);
            case 11: return new Color(245/256f,153/256f,113/256f,256f/256f);
            case 12: return new Color(189/256f,200/256f,240/256f,256f/256f);
            case 13: return new Color(128/256f,128/256f,0/256f,256f/256f);
            case 14: return new Color(128/256f,128/256f,128/256f,256f/256f);
            case 15: return new Color(256/256f,256/256f,256/256f,256f/256f);
            case 16: return new Color(0/256f,0/256f,0/256f,256f/256f);

            default: return new Color(1,1,1,1);
        }
    }
    //endregion
}