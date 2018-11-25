package com.sir.black.Tools.Character.InitialObject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Textures;
import com.sir.black.Tools.Character.IBaseObject;
import com.sir.black.Tools.Character.LayerRuler;
import com.sir.black.Tools.Special.SpecialMath;

/**
 * Start class for all objects
 * 08.01.18
 */

public class GameObject implements IBaseObject {
    //region static
    /**
     * Стандартний обєкт
     * @return new made object with new references
     */
    public static GameObject standart(){
        GameObject gameObject = new GameObject();
        return gameObject;
    }
    //endregion

    //region fields
    /**
     * Чи готове до видалення(занулення), для зовнішнього циклу
     */
    protected boolean readyToDelete; // Чи готове до видалення(занулення), для зовнішнього циклу

    /**
     * Текстура
     */
    private Texture texture; // Текстура
    /**
     * Позиція
     */
    protected Vector2 position; // Позиція
    /**
     * Позиція промальовки
     */
    protected Vector2 positionDraw; // Позиція промальовки
    /**
     * Зміщення для кручення
     */
    protected Vector2 origin; // Зміщення для кручення
    /**
     * Висота ширина
     */
    protected Vector2 WH; // Висота ширина
    /**
     * Маштаб зображення
     */
    protected Vector2 scale; // Маштаб зображення
    /**
     * Кут повороту
     */
    protected Vector2 rotation; // Кут повороту
    /**
     * Зміщення по Х для малювання картинки
     */
    protected int srcX; // Зміщення по Х для малювання картинки
    /**
     * Зміщення по Y для малювання картинки
     */
    protected int srcY; // Зміщення по Y для малювання картинки
    /**
     * Розміри картинки по ширині
     */
    protected int srcW; // Розміри картинки по ширині
    /**
     * Розміри картинки по висоті
     */
    protected int srcH; // Розміри картинки по висоті
    /**
     * Оборот по горизонталі
     */
    protected boolean flipX; // Оборот по горизонталі
    /**
     * Оборот по вертикалі
     */
    protected boolean flipY; // Оборот по вертикалі

    /**
     * Колір
     */
    protected Color color; // Колір

    /**
     * Промальовка шарів
     */
    protected float layer; // Промальовка шарів

    /**
     * Засіб для промальовки обєктів по слоям
     */
    protected LayerRuler layerRuler; // Засіб для промальовки обєктів по слоям
    //endregion

    //region construct/refreshExternalDependencies/default
    /**
     * Повний конструктор для простого обєкта
     * @param texture текстура
     * @param position позиція
     * @param origin зміщення центра кручення
     * @param WH розміри текстури яка буде промальовуватися
     * @param scale маштаб
     * @param rotation кручення
     * @param srcX початок промальовки частини картинки, точка на картинці Х
     * @param srcY початок промальовки частини картинки, точка на картинці Y
     * @param srcW довжина частини картинки яка буде промальовуватися
     * @param srcH ширина частини картинки яка буде промальовуватися
     * @param flipX поворот по осі Х
     * @param flipY поворто по осі Y
     * @param color фільтр кольору обєкта
     * @param layer шар на якому промальовується обєкт
     */
    public GameObject(Texture texture, Vector2 position, Vector2 origin, Vector2 WH, Vector2 scale,
                      float rotation, int srcX, int srcY, int srcW, int srcH, boolean flipX, boolean flipY,
                      Color color, float layer) {
        initialize(); // Значення за замовчуванням
        refreshExternalDependencies(); // Взято з архіва
        create(texture, position, origin, WH, scale, rotation,
                srcX, srcY, srcW, srcH, flipX, flipY, color, layer);
    }

    /**
     * Створити простий обєкт
     * @param texture текстура
     * @param position position from left lower corner
     * @param origin зміщення центра кручення
     * @param scale маштаб
     * @param rotation кручення
     * @param color фільтр кольору обєкта
     * @param layer шар на якому промальовується обєкт
     */
    public GameObject(Texture texture, Vector2 position, Vector2 origin, Vector2 scale,
                      float rotation, Color color, float layer) {
        initialize(); // Значення за замовчуванням
        refreshExternalDependencies(); // Взято з архіва
        create(texture, position, origin, new Vector2(texture.getWidth(), texture.getHeight()),
                scale, rotation, 0, 0, texture.getWidth(), texture.getHeight(),
                false,false, color, layer);
    }
    public GameObject(Texture texture, Vector2 position, float scale,
                       Color color, float layer) {
        initialize(); // Значення за замовчуванням
        refreshExternalDependencies(); // Взято з архіва
        create(texture, position, new Vector2(), new Vector2(texture.getWidth(), texture.getHeight()),
                new Vector2(scale, scale), 0, 0, 0, texture.getWidth(), texture.getHeight(),
                false,false, color, layer);
    }

    /**
     * Спрощений конструктор для простого обєкта
     * @param texture текстура
     * @param position позиція
     * @param scale маштаб
     */
    public GameObject(Texture texture, Vector2 position, Vector2 scale) {
        initialize(); // Значення за замовчуванням
        refreshExternalDependencies(); // Взято з архіва
        create(texture, position, new Vector2(), new Vector2(texture.getWidth(), texture.getHeight()),
                scale, 0, 0, 0, texture.getWidth(), texture.getHeight(),
                false,false, new Color(1,1,1,1), 0);
    }

    /**
     * Спрощений конструктор для простого обєкта
     * @param texture текстура
     * @param position позиція
     * @param sizeX size of picture X
     * @param sizeY size of picture Y
     */
    public GameObject(Texture texture, Vector2 position, float sizeX, float sizeY) {
        initialize(); // Значення за замовчуванням
        refreshExternalDependencies(); // Взято з архіва
        create(texture, position, new Vector2(), new Vector2(texture.getWidth(), texture.getHeight()),
                new Vector2(sizeX/texture.getWidth(), sizeY/texture.getHeight()),
                0, 0, 0, texture.getWidth(), texture.getHeight(),
                false,false, new Color(1,1,1,1), 0);
    }

    /**
     * Спрощений обєкт
     * @param texture текстура
     * @param position позиція
     */
    public GameObject(Texture texture, Vector2 position) {
        initialize(); // Значення за замовчуванням
        refreshExternalDependencies(); // Взято з архіва
        create(texture, position, new Vector2(), new Vector2(texture.getWidth(), texture.getHeight()),
                new Vector2(1, 1), 0, 0, 0, texture.getWidth(), texture.getHeight(),
                false,false, new Color(1,1,1,1), 0);
    }

    /**
     * Безпараметровий конструктор обєкта
     */
    public GameObject() {
        initialize(); // Значення за замовчуванням
        refreshExternalDependencies(); // Взято з архіва
    }

    /**
     * Клонування обєкта
     * @param gameObject обєкт який буде клонуватися
     */
    public GameObject(GameObject gameObject) {
        this.readyToDelete = gameObject.readyToDelete;
        this.texture = gameObject.texture;
        this.position = new Vector2();
        this.position.x = gameObject.position.x;
        this.position.y = gameObject.position.y;
        this.positionDraw = new Vector2();
        this.positionDraw.x = gameObject.positionDraw.x;
        this.positionDraw.y = gameObject.positionDraw.y;
        this.origin = gameObject.origin;
        this.WH = gameObject.WH;
        this.scale = gameObject.scale;
        this.rotation = new Vector2();
        this.rotation.x = gameObject.rotation.x;
        this.rotation.y = gameObject.rotation.y;
        this.srcX = gameObject.srcX;
        this.srcY = gameObject.srcY;
        this.srcW = gameObject.srcW;
        this.srcH = gameObject.srcH;
        this.flipX = gameObject.flipX;
        this.flipY = gameObject.flipY;
        this.color = new Color();
        this.color.r = gameObject.color.r;
        this.color.g = gameObject.color.g;
        this.color.b = gameObject.color.b;
        this.color.a = gameObject.color.a;
        this.layer = gameObject.layer;
        this.layerRuler = gameObject.layerRuler;
    }



    /**
     * Значення за замовчуванням
     */
    protected void initialize(){
        this.texture = Textures.pixel;
        this.position = new Vector2();
        this.positionDraw = new Vector2();
        this.origin = new Vector2();
        this.WH = new Vector2(this.texture.getWidth(), this.texture.getHeight());
        this.scale = new Vector2(1, 1);
        this.rotation = new Vector2();
        this.srcX = 0;
        this.srcY = 0;
        this.srcW = this.texture.getWidth();
        this.srcH = this.texture.getHeight();
        this.flipX = false;
        this.flipY = false;
        this.color = new Color(1,1,1,1);
        this.layer = 0;
        this.readyToDelete = false;
        this.layerRuler = null;
    }

    /**
     * Взято з архіва
     */
    protected void refreshExternalDependencies(){
        this.layerRuler = Fin.layerRuler;
    }

    /**
     * Повний конструктор для простого обєкта
     * @param texture текстура
     * @param position позиція
     * @param origin зміщення центра кручення
     * @param WH розміри текстури яка буде промальовуватися
     * @param scale маштаб
     * @param rotation кручення
     * @param srcX початок промальовки частини картинки, точка на картинці Х
     * @param srcY початок промальовки частини картинки, точка на картинці Y
     * @param srcW довжина частини картинки яка буде промальовуватися
     * @param srcH ширина частини картинки яка буде промальовуватися
     * @param flipX поворот по осі Х
     * @param flipY поворто по осі Y
     * @param color фільтр кольору обєкта
     * @param layer шар на якому промальовується обєкт
     */
    protected void create(Texture texture, Vector2 position, Vector2 origin, Vector2 WH,
                          Vector2 scale, float rotation, int srcX, int srcY, int srcW, int srcH,
                          boolean flipX, boolean flipY, Color color, float layer){
        this.texture = texture;
        this.position.x = position.x; this.position.y = position.y;
        this.positionDraw.x = position.x; this.positionDraw.y = position.y;
        this.origin.x = origin.x; this.origin.y = origin.y;
        this.WH.x = WH.x; this.WH.y = WH.y;
        this.scale.x = scale.x; this.scale.y = scale.y;
        this.rotation.x = rotation;
        this.srcX = srcX;
        this.srcY = srcY;
        this.srcW = srcW;
        this.srcH = srcH;
        this.flipX = flipX;
        this.flipY = flipY;
        this.color.r = color.r; this.color.g = color.g; this.color.b = color.b; this.color.a = color.a;
        this.layer = layer;
    }
    //endregion

    //region get/set/mod/spy
    /**
     * Скопіювати елемент
     * @return копія буде цього ж класу
     */
    public GameObject cpy() {
        GameObject gameObject = new GameObject();
        gameObject.readyToDelete = this.readyToDelete;
        gameObject.texture = this.texture; // ref
        gameObject.position = new Vector2();
        gameObject.position.x = this.position.x;
        gameObject.position.y = this.position.y;
        gameObject.positionDraw = new Vector2();
        gameObject.positionDraw.x = this.positionDraw.x;
        gameObject.positionDraw.y = this.positionDraw.y;
        gameObject.origin = new Vector2();
        gameObject.origin.x = this.origin.x;
        gameObject.origin.y = this.origin.y;
        gameObject.WH = new Vector2();
        gameObject.WH.x = this.WH.x;
        gameObject.WH.y = this.WH.y;
        gameObject.scale = new Vector2();
        gameObject.scale.x = this.scale.x;
        gameObject.scale.y = this.scale.y;
        gameObject.rotation = new Vector2();
        gameObject.rotation.x = this.rotation.x;
        gameObject.rotation.y = this.rotation.y;
        gameObject.srcX = this.srcX;
        gameObject.srcY = this.srcY;
        gameObject.srcW = this.srcW;
        gameObject.srcH = this.srcH;
        gameObject.flipX = this.flipX;
        gameObject.flipY = this.flipY;
        gameObject.color = new Color();
        gameObject.color.r = this.color.r;
        gameObject.color.g = this.color.g;
        gameObject.color.b = this.color.b;
        gameObject.color.a = this.color.a;
        gameObject.layer = this.layer;
        gameObject.layerRuler = this.layerRuler; // ref
        return gameObject;
    }

    /**
     * Скопіювати параметри
     * @param gameObject місце звідки буде скопійовано
     */
    public void set(GameObject gameObject) {
        this.readyToDelete = gameObject.readyToDelete;
        this.texture = gameObject.texture; // ref
        this.position.x = gameObject.position.x;
        this.position.y = gameObject.position.y;
        this.positionDraw.x = gameObject.positionDraw.x;
        this.positionDraw.y = gameObject.positionDraw.y;
        this.origin.x = gameObject.origin.x;
        this.origin.y = gameObject.origin.y;
        this.WH.x = gameObject.WH.x;
        this.WH.y = gameObject.WH.y;

        this.scale.x = gameObject.scale.x;
        this.scale.y = gameObject.scale.y;
        this.rotation.x = gameObject.rotation.x;
        this.rotation.y = gameObject.rotation.y;
        this.srcX = gameObject.srcX;
        this.srcY = gameObject.srcY;
        this.srcW = gameObject.srcW;
        this.srcH = gameObject.srcH;
        this.flipX = gameObject.flipX;
        this.flipY = gameObject.flipY;
        this.color.r = gameObject.color.r;
        this.color.g = gameObject.color.g;
        this.color.b = gameObject.color.b;
        this.color.a = gameObject.color.a;
        this.layer = gameObject.layer;
        this.layerRuler = gameObject.layerRuler;
    }

    public Texture getTexture() {
        return texture;
    }
    public Vector2 getPosition() {
        return position;
    }
    public Vector2 getPositionDraw() {
        return positionDraw;
    }
    public Vector2 getOrigin() {
        return origin;
    }
    public Vector2 getWH() {
        return WH;
    }
    public Vector2 getScale() {
        return scale;
    }
    public Vector2 getRotationFullXY() {
        return rotation;
    }
    public float getRotation() {
        return rotation.x;
    }
    public int getSrcX() {
        return srcX;
    }
    public int getSrcY() {
        return srcY;
    }
    public int getSrcW() {
        return srcW;
    }
    public int getSrcH() {
        return srcH;
    }
    public boolean isFlipX() {
        return flipX;
    }
    public boolean isFlipY() {
        return flipY;
    }
    public Color getColor() {
        return color;
    }
    public float getLayer() {
        return layer;
    }
    public boolean isReadyToDelete() {
        return readyToDelete;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
        this.WH.x = texture.getWidth();
        this.WH.y = texture.getHeight();
        this.srcW = texture.getWidth();
        this.srcH = texture.getHeight();
    }
    public void setPosition(Vector2 position) { this.position.x = position.x; this.position.y = position.y; }
    public void setPositionDraw(Vector2 positionDraw) {
        this.positionDraw.x = positionDraw.x; this.positionDraw.y = positionDraw.y;
    }
    public void setOrigin(Vector2 origin) {
        this.origin.x = origin.x; this.origin.y = origin.y;
    }
    public void setWH(Vector2 WH) { this.WH.x = WH.x; this.WH.y = WH.y; }
    public void setScale(Vector2 scale) { this.scale.x = scale.x; this.scale.y = scale.y; }
    public void setRotation(float rotation) {
        this.rotation.x = rotation;
    }
    public void setSrcX(int srcX) {
        this.srcX = srcX;
    }
    public void setSrcY(int srcY) {
        this.srcY = srcY;
    }
    public void setSrcW(int srcW) {
        this.srcW = srcW;
    }
    public void setSrcH(int srcH) {
        this.srcH = srcH;
    }
    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }
    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }
    public void setColor(Color color) {
        this.color.r = color.r; this.color.g = color.g; this.color.b = color.b; this.color.a = color.a;
    }
    public void setLayer(float layer) {
        this.layer = layer;
    }
    public void setReadyToDelete(boolean readyToDelete) {
        this.readyToDelete = readyToDelete;
    }

    public void modPosition(Vector2 position) {
        this.position.add(position);
    }
    public void modPositionDraw(Vector2 positionDraw) {
        this.positionDraw.add(positionDraw);
    }
    public void modOrigin(Vector2 origin) {
        this.origin.add(origin);
    }
    public void modWH(Vector2 WH) {
        this.WH.add(WH);
    }
    public void modScale(Vector2 scale) {
        this.scale.add(scale);
    }
    public void modRotation(float rotation) { this.rotation.x += rotation; }
    public void modColor(Color color) {
        this.color.r += color.r; this.color.g += color.g; this.color.b += color.b; this.color.a += color.a;
        //this.color.add(color);
    }
    public void modSrcX(int srcX) {
        this.srcX += srcX;
    }
    public void modSrcY(int srcY) {
        this.srcY += srcY;
    }
    public void modSrcW(int srcW) {
        this.srcW += srcW;
    }
    public void modSrcH(int srcH) {
        this.srcH += srcH;
    }

    public float getCurrentSizeX(){
        return scale.x * WH.x;
    }
    public float getCurrentSizeY(){
        return scale.y * WH.y;
    }
    /**
     * Реальні поточні розміри обєкта
     * @return реальний розмір
     */
    public Vector2 getCurrentSize(){
        return new Vector2(getCurrentSizeX(), getCurrentSizeY());
    }
    //endregion

    // region method
    /**
     * Чи готовий обєкт до того щоб його видалити
     */
    public void readyToDelete()
    {
        readyToDelete = true;
    }

    /**
     * Координати нижнього лівого кута
     * @return позиція нижнього лівого кута картинки
     */
    public Vector2 getDownLeft() { return new Vector2(position); }
    /**
     * Координати верхнього правого кута
     * @return координати верхнього правого кута картинки
     */
    public Vector2 getTopRight() {
        return new Vector2(position.x + getCurrentSizeX(), position.y + getCurrentSizeY());
    }
    /**
     * Координати верхнього лівого кута
     * @return позиція верхнього лівого кута картинки
     */
    public Vector2 getTopLeft() {
        return new Vector2(position.x, position.y + getCurrentSizeY());
    }
    /**
     * Координти нижнього правого кута картинки
     * @return нижній правий кут картинки
     */
    public Vector2 getDownRight() {
        return new Vector2(position.x + getCurrentSizeX(), position.y);
    }

    /**
     * Загальна перевірка чи попадає точка всередину нашої області
     * @param goForth дотик(якщо false то верне ні, якщо true то перевірить чи попало усередину
     * @param touch точка дотику
     * @return так, якщо дотик відбувся і якщо точка попала в середину
     */
    public boolean inSide(boolean goForth, Vector2 touch) {
        if (goForth) {
            return pointInRectangle(touch);
        } else {
            return false;
        }
    }
    /**
     * Чи потрапляє задана точка в наш прямокутник(на текстуру)
     * @param touch позиція яка може попасти на текстуру
     * @return так, якщо точка попала всередину текстури
     */
    protected boolean pointInRectangle(Vector2 touch) {
        if     ((touch.x > getDownLeft().x) &&
                (touch.y > getDownLeft().y) &&
                (touch.x < getTopRight().x) &&
                (touch.y < getTopRight().y))
            return true;
        else
            return false;
    }

    /**
     * Обновляємо стан кульки
     */
    public void update() { }

    /**
     * Додавання в цикл промальовки
     */
    public void draw() {
        try { layerRuler.add(this); } // Додаємо в цикл промальовки якщо це можливо, якщо ні то не додаємо
        catch (LayerRuler.OutOfMaxNumberException outOfMaxNumberException) {  }
    }

    /**
     * Промальовка цифри всередині кружочка
     * @param spriteBatch спрайт бетч
     */
    public void trueDraw(SpriteBatch spriteBatch) {
        if (texture != null) {
            drawPositionUpdate();
            spriteBatch.setColor(color);
            spriteBatch.draw(texture, positionDraw.x, positionDraw.y, origin.x, origin.y,
                    WH.x, WH.y, scale.x, scale.y, rotation.x, srcX, srcY, srcW, srcH, flipX, flipY)
            ;
        }
    }
    /**
     * Ставить текстуру на правильне місце
     * Для круглих обєктів - постановка центра картинки на точку позиції
     */
    protected void drawPositionUpdate() {
        positionDraw.x = position.x;
        positionDraw.y = position.y;
    }
    // endregion

    /**
     * Підтерти обєкт, висвободити текстуру
     */
    public void dispose() {
        texture.dispose();
    }
}
