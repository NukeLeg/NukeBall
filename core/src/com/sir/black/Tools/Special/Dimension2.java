package com.sir.black.Tools.Special;

import com.badlogic.gdx.math.Vector2;

/**
 * Vector2 в даблах
 * 29.01.2018.
 */

public class Dimension2 {
    //region fields
    /**
     * Позиція Х
     */
    public double x; // Позиція Х
    /**
     * Позиція Y
     */
    public double y; // Позиція Y
    //endregion

    //region construct
    /**
     *
     * @param x
     * @param y
     */
    public Dimension2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Dimension2() {
        this.x = 0;
        this.y = 0;
    }

    public Dimension2(double xy) {
        this.x = xy;
        this.y = xy;
    }

    public Dimension2(Dimension2 dimension2){
        this.x = dimension2.x;
        this.y = dimension2.y;
    }

    public Dimension2(Vector2 vector2){
        this.x = vector2.x;
        this.y = vector2.y;
    }

    /**
     * Зробити копію
     * @return копія цього Dimension2
     */
    public Dimension2 cpy () {
        return new Dimension2(this);
    }
    //endregion

    //region external
    public static double len (double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    public double len () {
        return Math.sqrt(x * x + y * y);
    }

    public static double len2 (double x, double y) {
        return x * x + y * y;
    }

    public double len2 () {
        return x * x + y * y;
    }

    public Dimension2 set (Dimension2 v) {
        x = v.x;
        y = v.y;
        return this;
    }

    /** Sets the components of this dimension
     * @param x The x-component
     * @param y The y-component
     * @return This vector for chaining */
    public Dimension2 set (double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Dimension2 nor () {
        double len = len();
        if (len != 0) {
            x /= len;
            y /= len;
        }
        return this;
    }

    public Dimension2 add (Dimension2 v) {
        x += v.x;
        y += v.y;
        return this;
    }

    /** Adds the given components to this dimension
     * @param x The x-component
     * @param y The y-component
     * @return This vector for chaining */
    public Dimension2 add (double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Dimension2 scl (double scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }

    /** Multiplies this dimension by a scalar
     * @return This dimension for chaining */
    public Dimension2 scl (double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Dimension2 scl (Dimension2 v) {
        this.x *= v.x;
        this.y *= v.y;
        return this;
    }

    public Dimension2 setZero () {
        this.x = 0;
        this.y = 0;
        return this;
    }
    //endregion
}