package com.sir.black.Tools.Character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.InitialObject.CircleObject;

/**
 * Our planet consists of many circle objects
 * It is defined by this class
 */
public class PlanetObject extends Character {
    //region field
    protected float relativeAngle; //Planet object must be described in polar coordinate system
    protected float distance; //distance from planet center
    protected Vector2 planetCenter;
    //endregion

    //region constructor
    /**
     * This is object, which the planet consists of
     * @param texture texture
     * @param planetCenter Crnter of the planet, which contains this object
     * @param angle angle in polar system
     * @param distance distance in polar system
     * @param radius game-in radius of circle object
     */
    public PlanetObject (Texture texture, Vector2 planetCenter, float angle, float distance, float radius){
        super(new CircleObject(texture,
                new Vector2(planetCenter.x + (float)(distance * Math.cos(angle)),
                        planetCenter.y + (float) (distance * Math.sin(angle))),
                radius));
        setDistance(distance);
        setAngle(angle);
        refreshExternalDependencies();
    }

    /**
     * constructor for planetObject
     * @param texture texture
     * @param position position
     * @param radius game-in radius
     */
    public PlanetObject(Texture texture, Vector2 position, float radius) {
        super(new CircleObject(texture, position, radius));
    }

    protected void refreshExternalDependencies(){
        planetCenter = new Vector2(Fin.planetCenter);
    }
    //endregion

    //region get/set/mod
    public float getAngle(){return relativeAngle;}
    public float getDistance(){return distance;}

    protected void setAngle(float angle){
        this.relativeAngle = angle;
        this.setPosition(planetCenter, angle, distance);
    }
    protected void setDistance(float distance){this.distance = distance;}
    //endregion

    public void rotatePlanetObject(float angle) {
        this.addAngle(angle);
    }
    protected void addAngle(float angle){
        setAngle(relativeAngle + angle);
    }
    protected void setPosition(Vector2 planetCenter, float angle, float distance) {
        this.relativeAngle = angle;
        this.distance = distance;
        super.setPosition(new Vector2(planetCenter.x + (float)(distance * Math.cos(angle)),
                planetCenter.y + (float) (distance * Math.sin(angle))));
    }
}
