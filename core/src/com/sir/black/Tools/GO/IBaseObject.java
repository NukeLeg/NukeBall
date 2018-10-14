package com.sir.black.Tools.GO;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;

public interface IBaseObject {
    //region get/set/mod
    public Texture getTexture();
    public Vector2 getPosition();
    public Vector2 getPositionDraw();
    public Vector2 getOrigin();
    public Vector2 getWH();
    public Vector2 getScale();
    public Vector2 getRotationFullXY();
    public float getRotation();
    public int getSrcX();
    public int getSrcY();
    public int getSrcW();
    public int getSrcH();
    public boolean isFlipX();
    public boolean isFlipY();
    public Color getColor();
    public float getLayer();
    public boolean isReadyToDelete();

    public void setTexture(Texture texture);
    public void setPosition(Vector2 position);
    public void setPositionDraw(Vector2 positionDraw);
    public void setOrigin(Vector2 origin);
    public void setWH(Vector2 WH);
    public void setScale(Vector2 scale);
    public void setRotation(float rotation);
    public void setSrcX(int srcX);
    public void setSrcY(int srcY);
    public void setSrcW(int srcW);
    public void setSrcH(int srcH);
    public void setFlipX(boolean flipX);
    public void setFlipY(boolean flipY);
    public void setColor(Color color);
    public void setLayer(float layer);
    public void setReadyToDelete(boolean readyToDelete);

    public void modPosition(Vector2 position);
    public void modPositionDraw(Vector2 positionDraw);
    public void modOrigin(Vector2 origin);
    public void modWH(Vector2 WH);
    public void modScale(Vector2 scale);
    public void modRotation(float rotation);
    public void modColor(Color color);
    public void modSrcX(int srcX);
    public void modSrcY(int srcY);
    public void modSrcW(int srcW);
    public void modSrcH(int srcH);
    //endregion

    // region external
    /**
     * Чи готовий обєкт до того щоб його видалити
     */
    public void readyToDelete();

    // Take part position of picture
    public Vector2 getDownLeft();
    public Vector2 getTopRight();
    public Vector2 getTopLeft();
    public Vector2 getDownRight();

    /**
     * Загальна перевірка чи попадає точка всередину нашої області
     * @param goForth дотик(якщо false то верне ні, якщо true то перевірить чи попало усередину
     * @param touch точка дотику
     * @return так, якщо дотик відбувся і якщо точка попала в середину
     */
    public boolean inSide(boolean goForth, Vector2 touch);

    /**
     * Реальні поточні розміри обєкта
     * @return реальний розмір
     */
    public Vector2 getCurrentSize();

    /**
     * Update state
     */
    public void update();

    /**
     * Додавання в цикл промальовки
     */
    public void draw();

    /**
     * Промальовка цифри всередині кружочка
     * @param spriteBatch спрайт бетч
     */
    public void trueDraw(SpriteBatch spriteBatch);
    // endregion

    /**
     * Підтерти обєкт, висвободити обєкти
     */
    public void dispose();
}
