package com.sir.black.Tools.Special;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;


/**
 * Клас для того щоб визначати і виводити дані(для перевірки)
 * 31.01.2018.
 */

public class Checker {
    //region fields
    /**
     * Колір
     */
    public static Color[] checkColor; // Кольори
    /**
     * Вектора для вивода
     */
    public static Vector2[] checkVector2; // Вектора для вивода
    /**
     * Флоати для вивода
     */
    public static Float[] checkFloat; // Флоати для вивода
    /**
     * Для вивода інформації про ці обєкти
     */
    public static String[] checkColorString, checkVector2String, checkFloatString; // Для вивода інформації про ці обєкти

    /**
     * Для промальовки текста
     */
    public static SpriteBatch spriteBatch; // Для промальовки текста
    /**
     * Шрифт для промальовки тексту
     */
    public static BitmapFont bitmapFont; // Шрифт для промальовки тексту
    //endregion

    //region construct
    static {
        int count = 10;
        checkColor = new Color[count];
        checkVector2 = new Vector2[count];
        checkFloat = new Float[count];

        checkColorString = new String[count];
        checkVector2String = new String[count];
        checkFloatString = new String[count];

        spriteBatch = Fin.spriteBatch;
        bitmapFont = Fin.bitmapFont60;
    }
    //endregion

    //region external
    /**
     * Задати вектор
     * @param value значення вектора
     * @param number комірка куди задаємо
     */
    public static void update(Vector2 value, int number, String string){
        if (number < 0) number =0;
        if (number > checkVector2.length) number = checkVector2.length-1;
        checkVector2[number] = value;
        checkVector2String[number] = string;
    }
    /**
     * Задати флоат
     * @param value значення вектора
     * @param number комірка куди задаємо
     */
    public static void update(float value, int number, String string){
        if (number < 0) number =0;
        if (number > checkFloat.length) number = checkFloat.length - 1;
        checkFloat[number] = value;
        checkFloatString[number] = string;
    }
    /**
     * Задати колір
     * @param value значення вектора
     * @param number комірка куди задаємо
     */
    public static void update(Color value, int number, String string){
        if (number < 0) number =0;
        if (number > checkColor.length) number = checkColor.length-1;
        checkColor[number] = value;
        checkColorString[number] = string;
    }

    /**
     * Вивести дані про вектора
     * @return рядок даних
     */
    public static String writeVector2(){
        String string = "";
        for (int i = 0; i < checkVector2.length; i++) {
            string += "" +
                    ((checkVector2[i] == null) ?
                            ""
                            :
                            ((checkVector2String[i] == null) ? "v" + i + ": " : checkVector2String[i] + ": ")
                                    + checkVector2[i].toString() + "\n")
            ;
        }
        return string;
    }
    /**
     * Вивести дані про флоати
     * @return рядок даних
     */
    public static String writeFloat(){
        String string = "";
        for (int i = 0; i < checkFloat.length; i++) {
            if (checkFloat[i]!=null)
                string += "" +
                        ((checkFloatString[i] == null) ? "f" + i + ": " : checkFloatString[i] + ": ")
                        + checkFloat[i] + "\n"
                        ;
        }
        return string;
    }
    /**
     * Вивести дані про кольори
     * @return рядок даних
     */
    public static String writeColor(){
        String string = "";
        for (int i = 0; i < checkColor.length; i++) {
            string += "" +
                    ((checkColor[i] == null) ?
                            ""
                            :
                            ((checkColorString[i] == null) ? "c" + i + ": " : checkVector2String[i] + ": ")
                                    + checkColor[i].toString() + "\n")
            ;
        }
        return string;
    }

    /**
     * Промалювати текст про вектора
     * @param position позиція промальовки
     */
    public static void drawVector2(Vector2 position){
        bitmapFont.draw(spriteBatch, writeVector2(), position.x, position.y);
    }
    /**
     * Промалювати текст про флоати
     * @param position позиція промальовки
     */
    public static void drawFloat(Vector2 position){
        bitmapFont.draw(spriteBatch, writeFloat(), position.x, position.y);
    }
    /**
     * Промалювати текст про кольори
     * @param position позиція промальовки
     */
    public static void drawColor(Vector2 position){
        bitmapFont.draw(spriteBatch, writeColor(), position.x, position.y);
    }
    //endregion
}