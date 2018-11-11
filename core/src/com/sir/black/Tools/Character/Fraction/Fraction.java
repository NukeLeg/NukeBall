package com.sir.black.Tools.Character.Fraction;

/**
 * Змінює частину картинку яку треба промалювати
 * 15.01.18
 */

public class Fraction {
    //region fields
    /**
     * Довжина картінкі
     */
    protected int width; // Довжина картінкі
    /**
     * Висота картинки
     */
    protected int height; // Висота картинки
    /**
     * Швидкість анімації
     */
    protected int speedAnimation; // Швидкість анімації
    protected int[] firstPictureInLine; // from 0 to max number
    protected int[] lastPictureInLine; // from 0 to max number
    /**
     * Кількості картинок в одній лінії
     */
    protected int[] picturesInLine; // Кількості картинок в одній лінії
    /**
     * Дорожка картинок на якій знаходиться графіка
     */
    protected int line; // Дорожка на якій знаходиться графіка
    /**
     * Номер картинки анімації по одній лінії
     */
    protected int point; // Номер картинки анімації по одній лінії
    /**
     * Лічильник картинок по одній лінії
     */
    protected int animationСounter; // Лічильник картинок по одній лінії
    /**
     * Повна анімація
     */
    protected boolean isFullAnimation; // Повна анімація
    //endregion

    //region set/get
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getSpeedAnimation() { return speedAnimation; }
    public int[] getPicturesInLine() { return picturesInLine; }
    public int getLine() { return line; }
    public int getPoint() { return point; }
    public int getAnimationСounter() { return animationСounter; }
    /**
     * Чи повна анімація, якщо так то проходить по всіх кадриках, якщо ні то тільки по рядку
     * @return так - повна анімація, ні - анімація по лінії
     */
    public boolean isFullAnimation() { return isFullAnimation; }

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public void setSpeedAnimation(int speedAnimation) {
        if (speedAnimation<=0) speedAnimation = 1;
        this.speedAnimation = speedAnimation;
    }
    public void setPicturesInLine(int[] picturesInLine) { this.picturesInLine = picturesInLine; }
    public void setLine(int line) {
        if (getPicturesInLine() != null &&
                line >= picturesInLine.length)
            line = picturesInLine.length - 1;
        this.line = line;
        setPoint(getPoint());
    }
    public void setPoint(int point) {
        if (getPicturesInLine() != null &&
                point < picturesInLine[line])
            this.point = point;
        else
            this.point = point % picturesInLine[line];
    }
    /**
     * Чи повна анімація, якщо так то проходить по всіх кадриках, якщо ні то тільки по рядку
     * @param fullAnimation так - повна анімація, ні - анімація по лінії
     */
    public void setFullAnimation(boolean fullAnimation) { isFullAnimation = fullAnimation; }
    //endregion

    //region construct/default
    /**
     * Задати параметри для анімації
     * @param width довжина картинки
     * @param height висота однієї картинки
     * @param speedAnimation швидкість анімації
     * @param picturesInLine масив який показує кількість картинок в одній горизонтальній лінії
     * @param line початкова горизонтальна лінія
     * @param point точка із горизонатльної лінії(певний фремй)
     */
    public Fraction(int width, int height, int speedAnimation, int[] picturesInLine, int line, int point, boolean isFullAnimation) {
        ///З конструктора
        this.width = width;
        this.height = height;
        this.picturesInLine = picturesInLine;

        setSpeedAnimation(speedAnimation);
        setLine(line);
        setPoint(point);

        setFullAnimation(isFullAnimation);

        defaultValue(); // Дефолтні значення
    }

    /**
     * Скорочений конструктор анімації
     * @param width ширина
     * @param height висота
     * @param picturesInLine масив який показує кількість картинок в одній горизонтальній лінії
     */
    public Fraction(int width, int height, int[] picturesInLine) {
        this(width, height, 0, picturesInLine, 0, 0, false);
    }

    /**
     * Для клонування обєкта
     * @param fraction обєкт з якого буде клонуватися
     */
    public Fraction(Fraction fraction) {
        this.width = fraction.width;
        this.height = fraction.height;
        this.speedAnimation = fraction.speedAnimation;
        if (fraction.picturesInLine != null) {
            this.picturesInLine = new int[fraction.picturesInLine.length];
            for (int i = 0; i < this.picturesInLine.length; i++) {
                this.picturesInLine[i] = fraction.picturesInLine[i];
            }
        }
        this.line = fraction.line;
        this.point = fraction.point;
        this.animationСounter = fraction.animationСounter;
        this.isFullAnimation = fraction.isFullAnimation;
    }

    /**
     * Скорочений конструктор анімації
     * @param width ширина
     * @param height висота
     */
    public Fraction(int width,int height){
        this(width, height, 0, new int[]{1}, 0, 0, false);
    }

    /**
     * Значення за замовчуванням
     */
    protected void defaultValue() {
        this.animationСounter = 0;
    }

    /**
     * Скопіювати параметри
     * @param fraction місце звідки буде скопійовано
     */
    public void copyParams(Fraction fraction) {
        this.width = fraction.width;
        this.height = fraction.height;
        this.speedAnimation = fraction.speedAnimation;
        if (fraction.picturesInLine != null) {
            this.picturesInLine = new int[fraction.picturesInLine.length];
            for (int i = 0; i < this.picturesInLine.length; i++) {
                this.picturesInLine[i] = fraction.picturesInLine[i];
            }
        }
        this.line = fraction.line;
        this.point = fraction.point;
        this.animationСounter = fraction.animationСounter;
        this.isFullAnimation = fraction.isFullAnimation;
    }

    /**
     * Створити копію цього ж обєкта
     * @return копія обєкта такого ж класу
     */
    public Fraction cpy(){
        Fraction fraction = new Fraction(this.width, this.height);
        fraction.width = this.width;
        fraction.height = this.height;
        fraction.speedAnimation = this.speedAnimation;
        if (this.picturesInLine != null) {
            fraction.picturesInLine = new int[this.picturesInLine.length];
            for (int i = 0; i < fraction.picturesInLine.length; i++) {
                fraction.picturesInLine[i] = this.picturesInLine[i];
            }
        }
        fraction.line = this.line;
        fraction.point = this.point;
        fraction.animationСounter = this.animationСounter;
        fraction.isFullAnimation = this.isFullAnimation;

        return fraction;
    }
    //endregion

    //region external
    /**
     * Обновлення анімації
     */
    public void update() {
        animationCount();
        animation();
    }

    /**
     * Видати позицію промальовки по Х
     * @return число на яке зміщена позиція по Х
     */
    public int changeSrcX() { return point * width; }

    /**
     * Видати позицію промальовки по Y
     * @return число на яке зміщена позиція на по Y
     */
    public int changeSrcY() { return line * height; }
    //endregion

    //region internal
    /**
     * Збільшення лічильника
     */
    protected void animationCount()
    {
        animationСounter++;
    }

    /**
     * Зміна частинки яку промальовуємо - анімація промальовки
     */
    protected void animation() {
        if (isFullAnimation) animationFull();
        else animationLine();
    }

    /**
     * Частична анімація,
     * анімація тільки по лінії
     */
    protected void animationLine(){
        if (picturesInLine != null && speedAnimation > 0)
            setPoint(this.point + ((animationСounter % speedAnimation == 0) ? 1 : 0));
    }

    /**
     * Повна анімація по всім картинкам до кінця
     */
    protected void animationFull() {
        if (picturesInLine != null && speedAnimation > 0) {
            int newPoint = this.point + ((animationСounter % speedAnimation == 0) ? 1 : 0);
            setPoint(newPoint);
            setLine((this.line + (newPoint / picturesInLine[line])) % this.picturesInLine.length);
        }
    }
    //endregion
}