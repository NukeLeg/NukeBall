package com.sir.black.Tools.Character.Physics;

import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Special.Checker;

public class PhysicalParameters {
    //region field
    protected float mass;
    protected Vector2 speed;
    protected Vector2 acceleration;
    protected float accelerationOfGravity;
    protected float rotationSpeed;
    protected float rotationAcceleration;

    protected GameObject gameObject;
    //endregion

    //region construct
    public PhysicalParameters(GameObject gameObject, float mass, Vector2 speed,
                              Vector2 acceleration, float rotationSpeed, float rotationAcceleration) {
        initialize();
        refreshExternalDependencies();
        create(gameObject, mass, speed, acceleration, rotationSpeed, rotationAcceleration);
    }

    public PhysicalParameters(GameObject gameObject) {
        initialize();
        refreshExternalDependencies();
        create(gameObject);
    }

    public PhysicalParameters(){
        initialize();
        refreshExternalDependencies();
    }

    protected void initialize() {
        mass = 0;
        speed = new Vector2();
        acceleration = new Vector2();
        accelerationOfGravity = 0;
        gameObject = null;
    }

    protected void refreshExternalDependencies() {
        this.mass = Fin.defaultMass;
        this.accelerationOfGravity = Fin.accelerationOfGravity;
    }

    protected void create(GameObject gameObject, float mass, Vector2 speed, Vector2 acceleration,
                          float rotationSpeed, float rotationAcceleration){
        this.gameObject = gameObject;
        this.mass = mass;
        if (speed!=null) this.speed = speed;
        if (acceleration != null) this.acceleration = acceleration;
        this.rotationSpeed = rotationSpeed;
        this.rotationAcceleration = rotationAcceleration;
    }
    protected void create(GameObject gameObject){
        this.gameObject = gameObject;
    }
    //endregion

    //region get/set
    public float getMass() {return mass;}
    public Vector2 getSpeed() {return speed;}
    public Vector2 getAcceleration() {return acceleration;}
    public float getAccelerationOfGravity() { return accelerationOfGravity; }
    public float getRotationSpeed() {return rotationSpeed;}
    public float getRotationAcceleration() {return rotationAcceleration;}
    public GameObject getGameObject() {return gameObject;}
    protected Vector2 getPosition() { return gameObject.getPosition(); }

    public void modMass(float mass) {this.mass += mass;}
    public void modSpeed(Vector2 speed) { if (speed != null) { this.speed.add(speed); } }
    public void modAcceleration(Vector2 acceleration) { if (acceleration != null) this.acceleration.add(acceleration);}
    public void modAccelerationOfGravity(float accelerationOfGravity) { this.accelerationOfGravity += accelerationOfGravity;}
    public void modRotationSpeed(float rotationSpeed) { this.rotationSpeed = rotationSpeed; }
    public void modRotationAcceleration(float rotationAcceleration) { this.rotationAcceleration = rotationAcceleration; }
    protected void modPosition(Vector2 position) { gameObject.modPosition(position); }
    public void setMass(float mass) {this.mass = mass;}

    public void setSpeed(Vector2 speed) {this.speed = speed;}
    public void setAcceleration(Vector2 acceleration) {this.acceleration = acceleration;}
    public void setAccelerationOfGravity(float accelerationOfGravity) { this.accelerationOfGravity = accelerationOfGravity; }
    public void setRotationSpeed(float rotationSpeed) {this.rotationSpeed = rotationSpeed;}
    public void setRotationAcceleration(float rotationAcceleration) {this.rotationAcceleration = rotationAcceleration;}
    public void setGameObject(GameObject gameObject) {this.gameObject = gameObject;}
    protected void setPosition(Vector2 position) { gameObject.setPosition(position); }
    //endregion

    public void rotateSpeed(float angle){ speed.rotate(angle); }

    public void makeInteraction(){

    }

    public void update(){
        updateGravity();
        updateSpeed();
        updateSpin();
    }

    protected void updateGravity() {

    }
    protected void updateSpeed(){
        if (speed != null && acceleration != null) {
            this.speed.add(acceleration);
            acceleration.set(0, 0);
        }
        if (speed != null && gameObject != null)
            this.gameObject.modPosition(speed);
    }
    protected void updateSpin(){
        rotationSpeed += rotationAcceleration;
        rotationAcceleration = 0;
        if (gameObject != null)
            this.gameObject.modRotation(rotationSpeed);
    }
}
