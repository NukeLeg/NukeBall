package com.sir.black.Tools.Character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Textures;
import com.sir.black.Tools.Character.InitialObject.CircleObject;
import com.sir.black.Tools.Character.Physics.PhysicalMovementGravity;

public class PlanetDestroyer extends Character {
    //region static
    public static PlanetDestroyer createDestroyer(float distance){
        return new PlanetDestroyer(Textures.circle, new Vector2(Fin.planetCenter.x, Fin.planetCenter.y + distance), Fin.defaultCircleRadius, 1);
    }
    public static PlanetDestroyer createDestroyer(Texture texture, float radius){
        return new PlanetDestroyer(texture, new Vector2(Fin.WIDTH / 2.0f, Fin.HEIGHT * 0.7f), radius, 1);
    }
    //endregion

    //region fields
    private Vector2 planetCenter;
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
        planetCenter = new Vector2(Fin.planetCenter);
    }
    protected void create(float mass){
        setMass(mass);
        physicalParameters = new PhysicalMovementGravity(gameObject, planetCenter);
    }
    //endregion


    public float getSpinAngle(){
        if (physicalParameters instanceof PhysicalMovementGravity)
            return ((PhysicalMovementGravity)(physicalParameters)).getSpinAngle();
        else
            return 0;
    }
    public void rotatePlanetObject(float angle, boolean isTouched) {
        if (physicalParameters instanceof PhysicalMovementGravity)
            ((PhysicalMovementGravity)(physicalParameters)).rotatePlanetObject(angle, isTouched);
    }
    public void interaction() {
        physicalParameters.makeInteraction();
    }


    @Override
    public void update() {
        super.update();
    }
}
