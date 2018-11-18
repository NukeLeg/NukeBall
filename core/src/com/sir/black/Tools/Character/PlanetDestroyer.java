package com.sir.black.Tools.Character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.InitialObject.CircleObject;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Character.Physics.PhysicalMovementGravity;
import com.sir.black.Tools.Special.SpecialMath;

public class PlanetDestroyer extends Character {
    //region static
    public static PlanetDestroyer createDestroyer(Texture texture, float radius){
        return new PlanetDestroyer(texture, new Vector2(Fin.WIDTH / 2.0f, Fin.HEIGHT * 0.7f), radius, 1);
    }
    //endregion

    //region fields
    private float angleOfHit;
    private Vector2 acceleration;
    //endregion

    //region constructor
    public PlanetDestroyer(Texture texture, Vector2 position, float radius, float mass) {
        super(new CircleObject(texture, position, radius));
        initializePlanetDestroyer();
        refreshExternalDependenciesPlanetDestroyer();
        create(mass);
    }
    protected void initializePlanetDestroyer() {
    }
    protected void refreshExternalDependenciesPlanetDestroyer() {
        this.acceleration = Fin.acceleration;
        this.angleOfHit = Fin.angleOfHit;
        setAcceleration(acceleration);
    }
    protected void create(float mass){
        setMass(mass);
        physicalParameters = new PhysicalMovementGravity(gameObject);
    }
    //endregion

    @Override
    public void update() {
        super.update();
    }

    public void interaction() {
        rotateSpeed(angleOfHit);
    }
}
