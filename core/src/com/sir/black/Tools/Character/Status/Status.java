package com.sir.black.Tools.Character.Status;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Textures;
import com.sir.black.Tools.Character.GameObject.GameObject;

/**
 * Відображає стан полоски даного обєкта
 * 20.01.18.
 */

public class Status {
    //region fields
    /**
     * Максимальне значення
     */
    public float max; // Максимальне значення
    /**
     * Поточні жизні
     */
    public float current; // Поточні жизні
    /**
     * Поточна швидкість востановлення жизняк
     */
    float healCurrentSpeed; // Поточна швидкість востановлення жизняк
    /**
     * Нормальна швидкість востановлення жизняк
     */
    float healNormalSpeed; // Нормальна швидкість востановлення жизняк
    /**
     * Чи дійшло до 0
     */
    boolean dead; // Чи дійшло до 0, Чи помер персонаж



    /**
     * Слой на якому промальовується голова для послоски статусу
     */
    float layerHealthHead; // Слой на якому промальовується голова для послоски статусу
    /**
     * Слой на якому промальовується рамка
     */
    float layerHealthFrame; // Слой на якому промальовується рамка
    /**
     * Слой на якому промальовується рівень жизняк
     */
    float layerHealthLevel; // Слой на якому промальовується рівень жизняк

    /**
     * Колір рівня жизняк для статуса
     */
    Color statusHeadColor; // Колір рівня жизняк для статуса1
    /**
     * Колір головної картинки для статуса
     */
    Color statusLevelColor; // Колір головної картинки для статуса
    /**
     * Колір рамки для статуса
     */
    Color statusFrameColor; // Колір рамки для статуса

    /**
     * Розміри текстури для промальовки біля меню
     */
    Vector2 statusHeadTextureSize; // Розміри текстури для промальовки біля меню

    /**
     * Дефолтні координати для лінії статуса
     */
    Vector2 statusCoords; // Дефолтні координати для лінії статуса



    /**
     * Рамка для жизняк
     */
    GameObject frame; // Кількість жизняк
    /**
     * Рівень жизняк
     */
    GameObject level; // Рівень жизняк
    /**
     * Картинка яка сопутствує статус жизняк
     */
    GameObject head; // Картинка яка сопутствує статус жизняк
    //endregion

    //region construct/refreshExternalDependencies/default
    /**
     * Лінійка даних
     * @param max максимальне значення (жизняк)
     * @param current поточне значення (жизняк)
     * @param healCurrentSpeed поточна швидкість збільшення(жизняк)
     * @param healNormalSpeed нормальна швидкість збільшення(жизняк)
     * @param dead чи дійшло вже до нуль(жизняк)
     */
    public Status(float max, float current, float healCurrentSpeed,
                  float healNormalSpeed, boolean dead) {
        // Вхідні параметри
        this.max = max;
        this.current = current;
        this.healCurrentSpeed = healCurrentSpeed;
        this.healNormalSpeed = healNormalSpeed;
        this.dead = dead;

        archive(); // Із архіва
        defaultValue(); // Дефолтні значення
    }

    /**
     * Лінійка даних
     * @param max максимальне значення (жизняк)
     * @param current поточне значення (жизняк)
     * @param healCurrentSpeed поточна швидкість збільшення(жизняк)
     */
    public Status(float max, float current, float healCurrentSpeed) {
        this(max, current, healCurrentSpeed, healCurrentSpeed, false);
    }

    /**
     * Лінійка даних, скорочений варіант
     * @param points Дефолтне значення жизняк і їх відповідний поточний розмір
     */
    public Status(float points)
    {
        this(points, points, 0, 0, false);
    }

    /**
     * Звичайний конструктор з заданими 100 жизняк і без хілкі
     */
    public Status() { this(Fin.status_standartLife, Fin.status_standartLife, 0, 0, false); }

    /**
     * Зробити клона
     * @param status клонувати з цього обєкта
     */
    public Status(Status status) {
        this.max = status.max;
        this.current = status.current;
        this.healCurrentSpeed = status.healCurrentSpeed;
        this.healNormalSpeed = status.healNormalSpeed;
        this.dead = status.dead;
        this.layerHealthHead = status.layerHealthHead;
        this.layerHealthFrame = status.layerHealthFrame;
        this.layerHealthLevel = status.layerHealthLevel;

        this.statusHeadColor = new Color(1,1,1,1);
        this.statusHeadColor.r = status.statusHeadColor.r;
        this.statusHeadColor.g = status.statusHeadColor.g;
        this.statusHeadColor.b = status.statusHeadColor.b;
        this.statusHeadColor.a = status.statusHeadColor.a;
        this.statusLevelColor = new Color(1,1,1,1);
        this.statusLevelColor.r = status.statusLevelColor.r;
        this.statusLevelColor.g = status.statusLevelColor.g;
        this.statusLevelColor.b = status.statusLevelColor.b;
        this.statusLevelColor.a = status.statusLevelColor.a;
        this.statusFrameColor = new Color(1,1,1,1);
        this.statusFrameColor.r = status.statusFrameColor.r;
        this.statusFrameColor.g = status.statusFrameColor.g;
        this.statusFrameColor.b = status.statusFrameColor.b;
        this.statusFrameColor.a = status.statusFrameColor.a;
        this.statusHeadTextureSize = new Vector2();
        this.statusHeadTextureSize.x = status.statusHeadTextureSize.x;
        this.statusHeadTextureSize.y = status.statusHeadTextureSize.y;
        this.statusCoords = new Vector2();
        this.statusCoords.x = status.statusCoords.x;
        this.statusCoords.y = status.statusCoords.y;
        this.frame = new GameObject(status.frame);
        this.level = new GameObject(status.level);
        this.head = new GameObject(status.head);
    }

    /**
     * Взято з архіва
     */
    protected void archive() {
        layerHealthHead = Fin.status_layerHealthHead;
        layerHealthLevel = Fin.status_layerHealthLevel;
        layerHealthFrame = Fin.status_layerHealthFrame;

        statusFrameColor = new Color(1,1,1,1);
        statusFrameColor.r = Fin.status_frameColor.r;
        statusFrameColor.g = Fin.status_frameColor.g;
        statusFrameColor.b = Fin.status_frameColor.b;
        statusFrameColor.a = Fin.status_frameColor.a;

        statusLevelColor = new Color(1,1,1,1);
        statusLevelColor.r = Fin.status_levelColor.r;
        statusLevelColor.g = Fin.status_levelColor.g;
        statusLevelColor.b = Fin.status_levelColor.b;
        statusLevelColor.a = Fin.status_levelColor.a;

        statusHeadColor = new Color(1,1,1,1);
        statusHeadColor.r = Fin.status_headColor.r;
        statusHeadColor.g = Fin.status_headColor.g;
        statusHeadColor.b = Fin.status_headColor.b;
        statusHeadColor.a = Fin.status_headColor.a;

        statusHeadTextureSize = new Vector2();
        statusHeadTextureSize.x = Fin.status_headTextureSize.x;
        statusHeadTextureSize.y = Fin.status_headTextureSize.y;

        statusCoords = new Vector2();
        statusCoords.x = Fin.status_coords.x;
        statusCoords.y = Fin.status_coords.y;
    }

    /**
     * Значення за замовчуванням
     */
    protected void defaultValue() {
        this.head = new GameObject(Textures.healthHead, new Vector2());
        this.head.setLayer(layerHealthHead);
        this.head.setColor(statusHeadColor);

        this.frame = new GameObject(Textures.healthHpFrame, new Vector2(Textures.healthHead.getWidth(),0));
        this.frame.setLayer(layerHealthFrame);
        this.frame.setColor(statusFrameColor);

        this.level = new GameObject(Textures.healthHpLev, new Vector2(Textures.healthHead.getWidth(),0));
        this.level.setLayer(layerHealthLevel);
        this.level.setColor(statusLevelColor);
    }

    /**
     * Скопіювати параметри
     * @param status місце звідки буде скопійовано
     */
    public void copyParams(Status status) {
        this.max = status.max;
        this.current = status.current;
        this.healCurrentSpeed = status.healCurrentSpeed;
        this.healNormalSpeed = status.healNormalSpeed;
        this.dead = status.dead;
        this.layerHealthHead = status.layerHealthHead;
        this.layerHealthFrame = status.layerHealthFrame;
        this.layerHealthLevel = status.layerHealthLevel;

        this.statusHeadColor.r = status.statusHeadColor.r;
        this.statusHeadColor.g = status.statusHeadColor.g;
        this.statusHeadColor.b = status.statusHeadColor.b;
        this.statusHeadColor.a = status.statusHeadColor.a;
        this.statusLevelColor.r = status.statusLevelColor.r;
        this.statusLevelColor.g = status.statusLevelColor.g;
        this.statusLevelColor.b = status.statusLevelColor.b;
        this.statusLevelColor.a = status.statusLevelColor.a;
        this.statusFrameColor.r = status.statusFrameColor.r;
        this.statusFrameColor.g = status.statusFrameColor.g;
        this.statusFrameColor.b = status.statusFrameColor.b;
        this.statusFrameColor.a = status.statusFrameColor.a;
        this.statusHeadTextureSize.x = status.statusHeadTextureSize.x;
        this.statusHeadTextureSize.y = status.statusHeadTextureSize.y;
        this.statusCoords.x = status.statusCoords.x;
        this.statusCoords.y = status.statusCoords.y;
        this.frame.set(status.frame);
        this.level.set(status.level);
        this.head.set(status.head);
    }

    /**
     * Скопіювати обєкт
     * @return копія цього обєкта
     */
    public Status cpy(){
        Status status = new Status();
        status.max = this.max;
        status.current = this.current;
        status.healCurrentSpeed = this.healCurrentSpeed;
        status.healNormalSpeed = this.healNormalSpeed;
        status.dead = this.dead;
        status.layerHealthHead = this.layerHealthHead;
        status.layerHealthFrame = this.layerHealthFrame;
        status.layerHealthLevel = this.layerHealthLevel;

        status.statusHeadColor = new Color(1,1,1,1);
        status.statusHeadColor.r = this.statusHeadColor.r;
        status.statusHeadColor.g = this.statusHeadColor.g;
        status.statusHeadColor.b = this.statusHeadColor.b;
        status.statusHeadColor.a = this.statusHeadColor.a;
        status.statusLevelColor = new Color(1,1,1,1);
        status.statusLevelColor.r = this.statusLevelColor.r;
        status.statusLevelColor.g = this.statusLevelColor.g;
        status.statusLevelColor.b = this.statusLevelColor.b;
        status.statusLevelColor.a = this.statusLevelColor.a;
        status.statusFrameColor = new Color(1,1,1,1);
        status.statusFrameColor.r = this.statusFrameColor.r;
        status.statusFrameColor.g = this.statusFrameColor.g;
        status.statusFrameColor.b = this.statusFrameColor.b;
        status.statusFrameColor.a = this.statusFrameColor.a;
        status.statusHeadTextureSize = new Vector2();
        status.statusHeadTextureSize.x = this.statusHeadTextureSize.x;
        status.statusHeadTextureSize.y = this.statusHeadTextureSize.y;
        status.statusCoords = new Vector2();
        status.statusCoords.x = this.statusCoords.x;
        status.statusCoords.y = this.statusCoords.y;
        status.frame = this.frame.cpy();
        status.level = this.level.cpy();
        status.head = this.head.cpy(); // FIXME: 31.01.2018

        return status;
    }
    //endregion

    //region get/set/mod
    public float getMax() { return max; }
    public float getCurrent() { return current; }
    /**
     * Чи персонаж здох?
     * @return повертає так, якщо здох
     */
    public boolean isDead() {
        return dead;
    }
    /**
     * Чи повна жизняка у персонажа
     * @return повертає так, якщо пована
     */
    public boolean isFull() { return (current >= max); }


    public void setMax(float max) { this.max = max; }
    public void setCurrent(float current) { this.current = current; }
    /**
     * Установити швидкість поточної хілкі
     * @param healPoint модифікатор який додає до поточної хілкі
     */
    public void setHealSpeed(float healPoint)
    {
        this.healCurrentSpeed = healPoint;
    }
    /**
     * Установити процент жизняк
     * @param percent процент від максимума
     */
    public void setPercentPoint(float percent) {
        this.current = percent * max;
        checkLife();
    }


    public void modMax(float max) { this.max = max; }
    public void modCurrent(float current) { this.current = current; }
    /**
     * Змінити швидкість поточної хілкі
     * @param healPoint модифікатор який додає до поточної хілкі
     */
    public void modHealSpeed(float healPoint)
    {
        this.healCurrentSpeed += healPoint;
    }
    /**
     * Змінити кількість жизняк
     * @param point модифікатор жизняк(додає)
     */
    public void modPoint(float point) {
        this.current += point;
        checkLife();
    }
    //endregion

    //region external
    /**
     * Обновлення жизняк
     */
    public void update() {
        checkLife();
        heal();
        updateLine();
    }

    /**
     * Установити координати для візуальних обєктів
     * @param position позиція де буде промальовуватися шкала
     */
    public void setLineCoords(Vector2 position) {
        head.setPosition(new Vector2(position.x, position.y));
        level.setPosition(new Vector2(position.x + head.getCurrentSize().x, position.y));
        frame.setPosition(new Vector2(position.x + head.getCurrentSize().x, position.y));
    }

    /**
     * Установити координати для візуальних обєктів за дефолтним значенням
     */
    public void setLineCoords() { setLineCoords(statusCoords); }

    /**
     * Змінює головну картинку для лінійки жазник
     * @param texture текстура
     * @param scrX початок промальовки Х
     * @param scrY початок промальовки Y
     * @param scrW розмір промальовки - ширина
     * @param scrH розмір промальовки - висота
     */
    public void changeHeadPicture(Texture texture, int scrX, int scrY, int scrW, int scrH) {
        this.head.setTexture(texture);
        this.head.setSrcW(scrX);
        this.head.setSrcY(scrY);
        this.head.setSrcW(scrW);
        this.head.setSrcH(scrH);
        //this.head.scaleStart = new Vector2(textureHeadSize.x/scrW, textureHeadSize.y/scrH);
    }

    /**
     * Змінює головну картинку для лінійки жазник
     * @param texture текстура
     */
    public void changeHeadPicture(Texture texture) {
        changeHeadPicture(texture, 0,0,texture.getWidth(), texture.getHeight());
    }

    /**
     * Промалювати обєкт
     * @param spriteBatch спрайтБетч
     */
    public void trueDraw(SpriteBatch spriteBatch) {
        head.trueDraw(spriteBatch);
        level.trueDraw(spriteBatch);
        frame.trueDraw(spriteBatch);
    }
    //endregion

    //region internal
    /**
     * Перевірити чи життя кінчилося
     */
    protected void checkLife() {
        if (current <= 0) {
            current = 0;
            dead = true;
        }
    }

    /**
     * Відновлення жизняк, само хілка
     */
    protected void heal() {
        if (dead) {} // Якщо мертвий то нічого не робити
        else
        { // Якщо живий і жизнякі менші за максимальні то додавати так жизнякі до максимума
            if (current<max)
            {
                current += healCurrentSpeed;
                if (current>max) current = max;
            }
        }
    }

    /**
     * Обновити показники лінії
     */
    protected void updateLine() {
        level.setSrcX((int)(level.getTexture().getWidth() * current / max));
        level.setWH(
                new Vector2(
                        (int)(level.getTexture().getWidth() * current / max),
                        level.getWH().y));
    }
    //endregion
}