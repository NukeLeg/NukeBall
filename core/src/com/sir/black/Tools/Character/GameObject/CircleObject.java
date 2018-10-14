import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Tools.Character.GameObject.GameObject;

public class CircleObject extends GameObject {
    //region fields
    /**
     * Radius for collision
     */
    float circleRadius; // Radius for collision
    //endregion

    //region construct
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
    public CircleObject(Texture texture, Vector2 position, Vector2 origin, Vector2 WH,
                        Vector2 scale, float rotation, int srcX, int srcY, int srcW, int srcH,
                        boolean flipX, boolean flipY, Color color, float layer, float circleRadius){
        initialize(); // Значення за замовчуванням
        refreshExternalDependencies(); // Взято з архіва
        create(texture, position, origin, WH, scale, rotation,
                srcX, srcY, srcW, srcH, flipX, flipY, color, layer, circleRadius);
    }

    /**
     * Спрощений конструктор для простого обєкта
     * @param texture текстура
     * @param position позиція
     * @param scale маштаб
     */
    public CircleObject(Texture texture, Vector2 position, Vector2 scale) {
        initialize(); // Значення за замовчуванням
        refreshExternalDependencies(); // Взято з архіва
        create(texture, position, new Vector2(), new Vector2(texture.getWidth(), texture.getHeight()),
                scale, 0, 0, 0, texture.getWidth(), texture.getHeight(),
                false,false, new Color(1,1,1,1), 0, 0);
    }

    /**
     * Remake object from gameObject to circleObject
     * @param gameObject
     */
    public CircleObject(GameObject gameObject){
        circleRadius = Math.min(getCenterOrigin().x, getCenterOrigin().y);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.circleRadius = 0;
    }

    protected void create(Texture texture, Vector2 position, Vector2 origin, Vector2 WH,
                          Vector2 scale, float rotation, int srcX, int srcY, int srcW,
                          int srcH, boolean flipX, boolean flipY, Color color,
                          float layer, float circleRadius) {
        super.create(texture, position, origin, WH, scale, rotation, srcX, srcY, srcW, srcH,
                flipX, flipY, color, layer);
        this.circleRadius = circleRadius;
        makeCircle();
    }

    /**
     * Зробити коло, виставити координати в центрі картинки
     */
    private void makeCircle() {
        origin.x = getCenterOrigin().x;
        origin.y = getCenterOrigin().y;
    }
    //endregion

    //region set/get
    public void setTexture(Texture texture) {
        super.setTexture(texture);
        makeCircle();
    }
    /**
     * Радіус кола
     * @return вернути довжину радіусв
     */
    public Vector2 getCenter() {
        return new Vector2(WH.x * scale.x / 2, WH.y  * scale.y / 2); // якщо коло то половина від довжинини
    }
    /**
     * Радіус кола без маштаба
     * @return вернути довжину радіусу без маштаба
     */
    public Vector2 getCenterOrigin() {
        return new Vector2(WH.x / 2, WH.y / 2); // якщо коло то половина від довжинини
    }
    //endregion

    //region method
    /**
     * Загальна перевірка чи попадає точка всередину нашої області
     * @param goForth дотик(якщо false то верне ні, якщо true то перевірить чи попало усередину
     * @param touch точка дотику
     * @return так, якщо дотик відбувся і якщо точка попала в середину
     */
    public boolean inSide(boolean goForth, Vector2 touch) {
        if (goForth) {
            return pointInCircle(touch);
        } else {
            return false;
        }
    }
    /**
     * Чи потрапляє задана точка в коло (текстура у вигляді кола
     * @param touch позиція дотику
     * @return так, якщо точка попала всередину текстури
     */
    protected boolean pointInCircle(Vector2 touch) {
        float radius = getCenter().x;
        if (Math.pow((position.x - touch.x), 2) +
                Math.pow((position.y - touch.y), 2)
                < Math.pow(radius * scale.x, 2))
            return true;
        else
            return false;
    }
    /**
     * Загальна перевірка чи попадає точка всередину нашої області
     * @param goForth дотик(якщо false то верне ні, якщо true то перевірить чи попало усередину
     * @param touch точка дотику
     * @return так, якщо дотик відбувся і якщо точка попала в середину
     */
    public boolean inSideCollision(boolean goForth, Vector2 touch) {
        if (goForth) {
            return pointInCircle(touch, circleRadius);
        } else {
            return false;
        }
    }
    /**
     * Чи потрапляє задана точка в коло (текстура у вигляді кола
     * @param touch позиція дотику
     * @param circleRadius radius for collision
     * @return так, якщо точка попала всередину текстури
     */
    protected boolean pointInCircle(Vector2 touch, float circleRadius) {
        float radius = getCenter().x;
        if (Math.pow((position.x - touch.x), 2) +
                Math.pow((position.y - touch.y), 2)
                < Math.pow(circleRadius * scale.x, 2))
            return true;
        else
            return false;
    }

    /**
     * Ставить текстуру на правильне місце
     * Для круглих обєктів - постановка центра картинки на точку позиції
     */
    protected void drawPositionUpdate() {
        positionDraw.x = position.x - getCenterOrigin().x;
        positionDraw.y = position.y - getCenterOrigin().y;
    }
    //endregion
}
