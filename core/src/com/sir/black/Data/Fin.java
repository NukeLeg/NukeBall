package com.sir.black.Data;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Tools.Character.LayerRuler;

/**
 * Клас всіх констант
 * 06.01.18
 */

public  class Fin {
    //region General
    /**
     * Засіб для промальовки обєктів
     */
    public static SpriteBatch spriteBatch; // Засіб для промальовки обєктів
    /**
     * Екземпляр тексту для промальовки
     */
    public static BitmapFont bitmapFont; // Екземпляр тексту для промальовки
    /**
     * Екземпляр тексту для промальовки TimesNewRoman 32
     */
    public static BitmapFont bitmapFont32; // Екземпляр тексту для промальовки TimesNewRoman 32
    /**
     * Екземпляр тексту для промальовки TimesNewRoman 60
     */
    public static BitmapFont bitmapFont60; // Екземпляр тексту для промальовки TimesNewRoman 60
    /**
     * Штука яка малює примімітіви
     */
    public static ShapeRenderer shapeRenderer; // Штука яка малює примімітіви
    /**
     * Колір фону
     */
    public static Color backGround; // Колір фону
    /**
     * Керувач промальовкою
     */
    public static LayerRuler layerRuler; // Керувач промальовкою

    /**
     * Ширина ігрової області
     */
    public static int WIDTH = 400;//1600;//Gdx.graphics.getWidth();//960;//540;
    /**
     * висота ігрової області
     * */
    public static int HEIGHT = 600;//1080;//Gdx.graphics.getHeight();//540;//960;
    /**
     * Ідеальна довжина ФулШДі
     */
    public static int HDWidth = 1080;//1920;
    /**
     * Ідеальна ширина ФулШДі
     */
    public static int HDHeight = 1920;//1080;

    /**
     * звуки в грі
     */
    public static boolean song = false; // звуки в грі
    /**
     * музика в грі
     */
    public static boolean music = false; // музика в грі

    /**
     * лічильник циклів від початку запуска
     */
    public static int counter = 0; // лічильник циклів від початку запуска
    /**
     * ігрова швидкість
     */
    public static Vector2 speedTime = new Vector2(1, 0); // ігрова швидкість
    /**
     * гравітація в грі
     */
    public static float FPS = 60; // ФПС
    //endregion
    //region layer
    /**
     * Задній фон
     */
    public static float layerBackGround = 0; // Задній фон
    /**
     * Сліди
     */
    public static float layerTraceObject = 1f; // Сліди
    /**
     * Обєкти з якими можна взаємодіяти
     */
    public static float layerSubObject = 2; // Обєкти з якими можна взаємодіяти
    /**
     * Персонажі
     */
    public static float layerCharObject = 3; // Персонажі
    /**
     * Вказівники на обєкти
     */
    public static float layerPointer = 4; // Вказівники на обєкти
    /**
     * Ачівки і повідомлення
     */
    public static float layerEffort = 5; // Ачівки і повідомлення
    //endregion
    //region layerRuler
    /**
     * Максимальна кількість текстурок які можна промалювати за допомогою LayerRuller
     */
    public static int layerRuler_maxDrawTextures = 500;
    // endregion

    //region Status
    /**
     * Слой на якому промальовується голова для послоски статусу
     */
    public static float status_layerHealthHead = 3; // Слой на якому промальовується голова для послоски статусу
    /**
     * Слой на якому промальовується рамка
     */
    public static float status_layerHealthFrame = 3.1f; // Слой на якому промальовується рамка
    /**
     * Слой на якому промальовується рівень жизняк
     */
    public static float status_layerHealthLevel = 3; // Слой на якому промальовується рівень жизняк

    /**
     * Колір головної картинки для статуса
     */
    public static Color status_headColor = new Color(1f,1f,1f,1);
    /**
     * Колір рамки для статуса
     */
    public static Color status_frameColor = new Color(0.9f,0.9f,0.9f,1);
    /**
     * Колір рівня жизняк для статуса
     */
    public static Color status_levelColor = new Color(0.7f,0.4f,0.7f,1);

    /**
     * Розміри текстури для промальовки біля меню
     */
    public static Vector2 status_headTextureSize = new Vector2(100,100);
    /**
     * Дефолтні координати для лінії статуса
     */
    public static Vector2 status_coords = new Vector2(200,360);
    /**
     * Жизнь по дефолту
     */
    public static float status_standartLife = 100; // Жизнь по дефолту
    //endregion

    //region thisGame
    public static int numberOfCharacters = 50;
    /**
     * Сліди
     */
    public static float defaultCircleRadius = 25.0f;
    /**
     * radiuses of the central circles
     */
    public static float CentralCircleRadius = 40.0f;
    /**
     * The planet have to be drawn with specified angle
     */
    public static float planetAngle = 0.01f;
    /**
     * where centra circle should be drawn
     */
    public static Vector2 planetCenter = new Vector2(WIDTH / 2, -100);
    /**
     * number of planet layers
     */
    public static float accelerationOfGravity = -0.25f; // gravity
    public static float jumpSpeed = 11; // vertical speed ball after hitting

    public static int numberOfLayers = 2;

    public static float defaultMass = 1;

    public static float angeleSpeedDissipation = 0.965f;
    public static float speedOfSpinning = 20f;
    //endregion

    //region construct
    /**
     * Загрузка всіх констант які не змогли загрузитися зразу
     */
    public Fin() {
        archive();
        defaultValue();
    }

    /**
     * Взято із архіва
     */
    protected void archive() {
        bitmapFont32 = new BitmapFont(Textures.timeNewRoman32); /** Екземпляр тексту для промальовки */
        bitmapFont60 = new BitmapFont(Textures.timeNewRoman60); /** Екземпляр тексту для промальовки */
    }

    /**
     * Значення за замовчуванням
     */
    protected void defaultValue() {
        spriteBatch = new SpriteBatch(); /**Засіб для промальовки обєктів*/
        shapeRenderer = new ShapeRenderer(); /**Засіб для промальовки примітив*/
        backGround = new Color(256/256f,256/256f,256/256f,1); /**Колір заднього фону*/
        backGround = new Color(0/256f,190/256f,220/256f,1); /**Колір заднього фону*/
        bitmapFont = new BitmapFont(); /** Екземпляр тексту для промальовки */
        counter = 0; /**Головний лічильник*/
        speedTime = new Vector2(1, 0); // ігрова швидкість
        FPS = 60; /**Стандартний ФПС на початку, потім вирахується*/
        layerRuler = new LayerRuler(spriteBatch, shapeRenderer); // Керувач промальовками
    }
    //endregion
}
