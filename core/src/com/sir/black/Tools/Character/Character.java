package com.sir.black.Tools.Character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Tools.Character.Behaviour.Behaviour;
import com.sir.black.Tools.Character.Condition.Condition;
import com.sir.black.Tools.Character.Fraction.Fraction;
import com.sir.black.Tools.Character.InitialObject.CircleObject;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Character.Physics.PhysicalParameters;
import com.sir.black.Tools.Character.Shader.ShaderRule;
import com.sir.black.Tools.Character.Status.Status;
import com.sir.black.Tools.Special.Checker;

/**
 * Created by NoOne on 10.09.2018.
 */

public class Character implements IBaseObject {
    //region static
    protected static boolean isExist(GameObject gameObject){
        if (gameObject != null) return true;
        else return false;
    }
    //endregion

    //region fields
    /**
     * Обєкт картинки
     */
    protected GameObject gameObject; // Обєкт картинки
    /**
     * Поведінка частинки
     */
    protected Behaviour behaviour; // Поведінка частинки
    /**
     * Частина картинки яка промальовується
     */
    protected Fraction fraction; // Частина картинки яка промальовується
    /**
     * Стан обєкту
     */
    protected Condition condition; // Стан обєкту
    /**
     * Статус жизняк персонажа
     */
    protected Status status; // Статус жизняк персонажа
    /**
     * Шейдер і його керування
     */
    protected ShaderRule shaderRule; // Шейдер і його керування
    /**
     * speed, mass,...
     */
    protected PhysicalParameters physicalParameters; // speed, mass,...
    //endregion

    //region construct
    public Character(GameObject gameObject, Behaviour behaviour, Fraction fraction, Condition condition, Status status, ShaderRule shaderRule) {
        initialize();
        refreshExternalDependencies();
        create(gameObject, behaviour, fraction, condition, status, shaderRule);
        update();
    }

    public Character(GameObject gameObject, Fraction fraction) {
        initialize();
        refreshExternalDependencies();
        create(gameObject, fraction);
        update();
    }

    public Character(GameObject gameObject) {
        initialize();
        refreshExternalDependencies();
        create(gameObject);
        update();
    }

    protected void initialize(){
        this.gameObject = new GameObject();
        this.behaviour = new Behaviour();
        //this.fraction = new Fraction(gameObject);
        this.condition = Condition.LIVE;
        this.status = new Status();
        this.shaderRule = new ShaderRule();
        this.physicalParameters = new PhysicalParameters(gameObject);
    }
    protected void refreshExternalDependencies() { }
    protected void create(GameObject gameObject, Behaviour behaviour, Fraction fraction,
                          Condition condition, Status status, ShaderRule shaderRule) {
        this.gameObject = gameObject;
        this.behaviour = behaviour;
        this.fraction = fraction;
        this.condition = condition;
        this.status = status;
        this.shaderRule = shaderRule;
        physicalParameters.setGameObject(gameObject);
    }
    protected void create(GameObject gameObject, Fraction fraction) {
        this.gameObject = gameObject;
        this.fraction = fraction;
        physicalParameters.setGameObject(gameObject);
    }
    protected void create(GameObject gameObject) {
        this.gameObject = gameObject;
        physicalParameters.setGameObject(gameObject);
    }
    //endregion

    //region override
    @Override
    public Texture getTexture() { return gameObject.getTexture(); }
    public Vector2 getPosition() { return gameObject.getPosition(); }
    public Vector2 getPositionDraw() { return gameObject.getPositionDraw(); }
    public Vector2 getOrigin() { return gameObject.getOrigin(); }
    public Vector2 getWH() { return gameObject.getWH(); }
    public Vector2 getScale() { return gameObject.getScale(); }
    public Vector2 getRotationFullXY() { return gameObject.getRotationFullXY(); }
    public float getRotation() { return gameObject.getRotation(); }
    public int getSrcX() { return gameObject.getSrcX(); }
    public int getSrcY() { return gameObject.getSrcY(); }
    public int getSrcW() { return gameObject.getSrcW(); }
    public int getSrcH() { return gameObject.getSrcH(); }
    public boolean isFlipX() { return gameObject.isFlipX(); }
    public boolean isFlipY() { return gameObject.isFlipY(); }
    public Color getColor() { return gameObject.getColor(); }
    public float getLayer() { return gameObject.getLayer(); }
    public boolean isReadyToDelete() { return gameObject.isReadyToDelete(); }

    @Override
    public void setTexture(Texture texture) { gameObject.setTexture(texture); }
    public void setPosition(Vector2 position) { gameObject.setPosition(position); }
    public void setPositionDraw(Vector2 positionDraw) { gameObject.setPositionDraw(positionDraw); }
    public void setOrigin(Vector2 origin) { gameObject.setOrigin(origin); }
    public void setWH(Vector2 WH) { gameObject.setWH(WH); }
    public void setScale(Vector2 scale) { gameObject.setScale(scale); }
    public void setRotation(float rotation) { gameObject.setRotation(rotation); }
    public void setSrcX(int srcX) { gameObject.setSrcX(srcX); }
    public void setSrcY(int srcY) { gameObject.setSrcY(srcY); }
    public void setSrcW(int srcW) { gameObject.setSrcW(srcW); }
    public void setSrcH(int srcH) { gameObject.setSrcH(srcH); }
    public void setFlipX(boolean flipX) { gameObject.setFlipX(flipX); }
    public void setFlipY(boolean flipY) { gameObject.setFlipY(flipY); }
    public void setColor(Color color) { gameObject.setColor(color); }
    public void setLayer(float layer) { gameObject.setLayer(layer); }
    public void setReadyToDelete(boolean readyToDelete) { gameObject.setReadyToDelete(readyToDelete); }

    @Override
    public void modPosition(Vector2 position) { gameObject.modPosition(position); }
    public void modPositionDraw(Vector2 positionDraw) { gameObject.modPositionDraw(positionDraw); }
    public void modOrigin(Vector2 origin) { gameObject.modOrigin(origin); }
    public void modWH(Vector2 WH) { gameObject.modWH(WH); }
    public void modScale(Vector2 scale) { gameObject.modScale(scale); }
    public void modRotation(float rotation) { gameObject.modRotation(rotation); }
    public void modColor(Color color) { gameObject.modColor(color); }
    public void modSrcX(int srcX) { gameObject.modSrcX(srcX); }
    public void modSrcY(int srcY) { gameObject.modSrcY(srcY); }
    public void modSrcW(int srcW) { gameObject.modSrcW(srcW); }
    public void modSrcH(int srcH) { gameObject.modSrcH(srcH); }

    @Override
    public void readyToDelete() { if (isExist(gameObject)) gameObject.readyToDelete(); }

    @Override
    public Vector2 getDownLeft() { return gameObject.getDownLeft(); }
    public Vector2 getTopRight() { return gameObject.getTopRight(); }
    public Vector2 getTopLeft() { return gameObject.getTopLeft(); }
    public Vector2 getDownRight() { return gameObject.getDownRight(); }

    @Override
    public boolean inSide(boolean goForth, Vector2 touch) { return gameObject.inSide(goForth, touch); }

    @Override
    public Vector2 getCurrentSize() { return gameObject.getCurrentSize(); }

    @Override
    public void update() {
        updatePhysicalParameters();

        updateGameObject();
        updateStatus(); // Для визначення жизняк
        updateCondition(); // Для перевизначення стану обєкта

        updateConduct(); // Обновлення стану обєкта(ПОЗИЦІЯ,ZiЧАС,КРУЧЕННЯ,РОЗШИРЕННЯ,КОЛІР)
        updateFraction(); // Обновлення анімації
        updateShaderRule(); // Обновити стан шейдера

    }

    @Override
    public void draw() { gameObject.draw(); }

    @Override
    public void trueDraw(SpriteBatch spriteBatch) {
        //drawPartOfPictureUpdate(); // Обновлення частини обєкта який буде промальовуватися
        //shaderRuleBegin(); // Почати шейдер
        gameObject.trueDraw(spriteBatch);
        //shaderRuleEnd();
    }

    @Override
    public void dispose() { gameObject.dispose(); }
    //endregion

    //region get/set
    public Vector2 getCenter(){
        if (gameObject instanceof CircleObject){
            return ((CircleObject)gameObject).getCenter();
        }
        else return null;
    }
    public float getRadius(){
        if (gameObject instanceof CircleObject){
            return ((CircleObject)gameObject).getCircleRadius();
        }
        else return 0;
    }

    public float getMass() { return physicalParameters.getMass(); }
    public Vector2 getSpeed() { return physicalParameters.getSpeed(); }
    public Vector2 getAcceleration() { return physicalParameters.getAcceleration(); }
    public float getRotationSpeed() { return physicalParameters.getRotationSpeed(); }
    public float getRotationAcceleration() { return physicalParameters.getRotationAcceleration(); }

    public void modMass(float mass) { physicalParameters.modMass(mass); }
    public void rotateSpeed(float angle) { physicalParameters.rotateSpeed(angle); }
    public void modSpeed(Vector2 speed) { physicalParameters.modSpeed(speed); }
    public void modAcceleration(Vector2 acceleration) { if (physicalParameters != null) physicalParameters.modAcceleration(acceleration); }
    public void modRotationSpeed(float rotationSpeed) { physicalParameters.modRotationSpeed(rotationSpeed); }
    public void modRotationAcceleration(float rotationAcceleration) { physicalParameters.modRotationAcceleration(rotationAcceleration); }
    public void setMass(float mass) { physicalParameters.setMass(mass); }
    public void setSpeed(Vector2 speed) { physicalParameters.setSpeed(speed); }
    public void setAcceleration(Vector2 acceleration) { physicalParameters.setAcceleration(acceleration); }
    public void setRotationSpeed(float rotationSpeed) { physicalParameters.setRotationSpeed(rotationSpeed); }
    public void setRotationAcceleration(float rotationAcceleration) { physicalParameters.setRotationAcceleration(rotationAcceleration); }



    public PhysicalParameters getPhysicalParameters() {return physicalParameters;}
    public GameObject getGameObject() { return gameObject; }
    public Behaviour getBehaviour() { return behaviour; }
    public Fraction getFraction() { return fraction; }
    public Condition getCondition() { return condition; }
    public Status getStatus() { return status; }
    public ShaderRule getShaderRule() { return shaderRule; }

    public void setPhysicalParameters(PhysicalParameters physicalParameters) {this.physicalParameters = physicalParameters;}
    public void setGameObject(GameObject gameObject) { this.gameObject = gameObject; }
    public void setBehaviour(Behaviour behaviour) { this.behaviour = behaviour; }
    public void setFraction(Fraction fraction) { this.fraction = fraction; }
    public void setCondition(Condition condition) { this.condition = condition; }
    public void setStatus(Status status) { this.status = status; }
    public void setShaderRule(ShaderRule shaderRule) { this.shaderRule = shaderRule; }
    //endregion

    //region method
    public void rotatePlanetObject(float angle) { }

    protected void updatePhysicalParameters(){
        if (physicalParameters != null) physicalParameters.update();
    }
    protected void updateGameObject(){
        gameObject.update();
    }
    /**
     * Обновлення жизняк персонажа
     */
    protected void updateStatus() { /*status.update(); checkLife();*/ }
    /**
     * Обновити стан обєкта
     */
    protected void updateCondition() { }
    /**
     * Обновлення стану обєкта(ПОЗИЦІЯ,ZiЧАС,КРУЧЕННЯ,РОЗШИРЕННЯ,КОЛІР,ЧАСТИНА ПРОМАЛЬОВКИ)
     */
    protected void updateConduct() { /*behaviour.update(condition, position, scale, color, rotation, null); */}
    /**
     * Обновлення анімації
     */
    protected void updateFraction() {
        if (fraction != null) {
            fraction.update();
            gameObject.setSrcX(fraction.changeSrcX());
            gameObject.setSrcY(fraction.changeSrcY());
            gameObject.setSrcW(fraction.getWidth());
            gameObject.setSrcH(fraction.getHeight());
            gameObject.setWH(new Vector2(fraction.getWidth(), fraction.getHeight()));
        }
    }
    /**
     * Обновлення шейдера
     */
    protected void updateShaderRule() { /*shaderRule.update();*/ }

    /**
     * Почати шейдер
     */
    protected void shaderRuleBegin() { if (shaderRule != null) shaderRule.shaderBegin(); }

    /**
     * Завершити шейдер
     */
    protected void shaderRuleEnd() { if (shaderRule != null) shaderRule.shaderEnd(); }
    //endregion
}
