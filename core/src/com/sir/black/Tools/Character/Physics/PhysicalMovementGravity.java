package com.sir.black.Tools.Character.Physics;

import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.InitialObject.GameObject;

public class PhysicalMovementGravity extends PhysicalParameters {
    Vector2 pointOfGravity;

    public PhysicalMovementGravity(GameObject gameObject, Vector2 pointOfGravity) {
        super(gameObject);
        initializePhysicalMovementGravity();
        createMovementGravity(pointOfGravity);
    }
    public PhysicalMovementGravity(GameObject gameObject){
        super(gameObject);
        initializePhysicalMovementGravity();
    }
    protected void initializePhysicalMovementGravity(){
        pointOfGravity = new Vector2();
    }
    protected void refreshExternalDependencies() {
        pointOfGravity = new Vector2(Fin.planetCenter);
    }
    protected void createMovementGravity(Vector2 pointOfGravity){
        this.pointOfGravity = new Vector2(pointOfGravity);
    }

    @Override
    protected void updateGravity() {
        acceleration.y = Math.signum(pointOfGravity.y - gameObject.getPosition().y);
    }
}
