package com.sir.black.Tools.Special;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Чотирьохмірна величина
 * 13.01.18
 */

public class Vector4 {
    //region fields
    /**
     * Координата X
     */
    public float x; // // Координата X
    /**
     * Координата Y
     */
    public float y; // Координата Y
    /**
     * Координата Z
     */
    public float z; // Координата Z
    /**
     * Координата W
     */
    public float w; // Координата W
    //endregion

    //region construct
    /**
     * Дефолтний 0,0,0,0
     */
    public Vector4() {this.x = 0; this.y = 0; this.z = 0; this.w = 0;}

    /**
     * Вектор 4 зібраний з двох двойних
     * @param v1
     * @param v2
     */
    public Vector4(Vector2 v1, Vector2 v2) {
        if (v1 != null) {
            this.x = v1.x;
            this.y = v1.y;
        }
        if (v2 != null) {
            this.z = v1.x;
            this.w = v1.y;
        }
    }

    /**
     * Задати вектор 4
     * @param x X
     * @param y Y
     * @param z Z
     * @param w W
     */
    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Задати одне число яке буде для всіх
     * @param xyzw одне число яке буде у всіх чотирьох координат
     */
    public Vector4(float xyzw) {
        this.x = xyzw;
        this.y = xyzw;
        this.z = xyzw;
        this.w = xyzw;
    }

    /**
     * Зробити з кольора вектор4
     * @param color колір
     */
    public Vector4(Color color){
        this.x = color.r;
        this.y = color.g;
        this.z = color.b;
        this.w = color.a;
    }

    /**
     * Для клонування обєкта
     * @param vector4 обєкт з якого клонуємо
     */
    public Vector4(Vector4 vector4){
        this.x = vector4.x;
        this.y = vector4.y;
        this.z = vector4.z;
        this.w = vector4.w;
    }

    /**
     * Скопіювати параметри
     * @param vector4 місце звідки буде скопійовано
     */
    public void copyParams(Vector4 vector4) {
        this.x = vector4.x;
        this.y = vector4.y;
        this.z = vector4.z;
        this.w = vector4.w;
    }
    //endregion

    //region external
    /**
     * Перевизначити вектор так щоб не мінялася його ссилка
     * @param vector4 новий вектор
     */
    public void setThis(Vector4 vector4) {
        this.x = vector4.x;
        this.y = vector4.y;
        this.z = vector4.z;
        this.w = vector4.w;
    }

    /**
     * Додати до вектора ще вектор
     * @param vector4
     */
    public void add(Vector4 vector4) {
        this.x += vector4.x;
        this.y += vector4.y;
        this.z += vector4.z;
        this.w += vector4.w;
    }

    /**
     * Помножити на скаляр
     * @param scalar скаляр
     * @return цей же вектор помножений на скаляр
     */
    public Vector4 scl (float scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
        w *= scalar;
        return this;
    }

    @Override
    public String toString() {
        return
                "x: " + x + " y: " + y + " z: " + z + " w: " + w;
    }
    //endregion
}
