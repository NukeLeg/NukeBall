package com.sir.black.Tools.Character.Physics;

import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Special.Checker;

public class PhysicalMovementGravity extends PhysicalParameters {
    //region field
    protected float speedToCenter;
    protected float speedAzimuth;
    protected float speedOfSpinning; // multiply angleAzimuth
    protected Vector2 distance; // distance between destroyer and the Earth
    protected float angeleSpeedDissipation;
    protected boolean isTouched;
    protected float jumpSpeed;

    protected Vector2 pointOfGravity; // dot of centre of spinning/middle of the Earth
    protected Vector2 newAcceleration; // add new accelerationOfGravity for usual accelerationOfGravity
    //endregion

    //region construct
    public PhysicalMovementGravity(GameObject gameObject, Vector2 pointOfGravity) {
        super(gameObject);
        initializePhysicalMovementGravity();
        refreshExternalDependencies();
        createMovementGravity(pointOfGravity);
    }
    public PhysicalMovementGravity(GameObject gameObject){
        super(gameObject);
        refreshExternalDependencies();
        initializePhysicalMovementGravity();
    }
    protected void initializePhysicalMovementGravity(){
        speedToCenter = 0;
        speedAzimuth = 0;
        isTouched = false;

        pointOfGravity = new Vector2();
        newAcceleration = new Vector2();
    }
    protected void refreshExternalDependencies() {
        super.refreshExternalDependencies();
        pointOfGravity = new Vector2(Fin.planetCenter);
        speedOfSpinning = Fin.speedOfSpinning;
        angeleSpeedDissipation = Fin.angeleSpeedDissipation;
        jumpSpeed = Fin.jumpSpeed;
    }
    protected void createMovementGravity(Vector2 pointOfGravity){
        this.pointOfGravity = new Vector2(pointOfGravity);
    }
    //endregion


    public void makeInteraction(){
        speedToCenter = jumpSpeed;
    }

    public void rotatePlanetObject(float angle, boolean isTouched) {
        speedAzimuth += angle * speedOfSpinning;
        this.isTouched = isTouched;
    }

    public float getSpinAngle(){
        distance = new Vector2(getPosition()).sub(pointOfGravity);
        return (distance.angle() + 270) % 360;
    }

    //region update
    @Override
    public void update() {
        spinRoundThePlanet();
        updateAcceleration();
        updateSpeedDissipation();

        super.update();
    }
    private void updateAcceleration() {
        speedToCenter += accelerationOfGravity;
    }
    private void updateSpeedDissipation() {
        if (isTouched) { speedAzimuth *= Math.pow(angeleSpeedDissipation, 3);}
        else speedAzimuth = speedAzimuth * angeleSpeedDissipation;
    }
    protected void spinRoundThePlanet() {
        distance = new Vector2(getPosition()).sub(pointOfGravity);
        Vector2 addAzimuthalSpeed = new Vector2(distance).rotate(speedAzimuth);
        addAzimuthalSpeed = addAzimuthalSpeed.sub(distance);

        distance.nor();
        Vector2 addVerticalSpeed = new Vector2(distance.x * speedToCenter, distance.y * speedToCenter);

        speed.x = addAzimuthalSpeed.x + addVerticalSpeed.x;
        speed.y = addAzimuthalSpeed.y + addVerticalSpeed.y;
    }

    //endregion
}
