package com.sir.black.Tools.Character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.InitialObject.CircleObject;
import com.sir.black.Tools.Special.SpecialMath;
import com.sir.black.Tools.Special.ZeroFun;

public class PlanetDestroyer extends Character/* implements Gravity*/ {
    private final float angleOfHeat = 180;
    private Vector2 planetCenter;

    private float mass;
    private Vector2 speed;

    //region constructor
    public PlanetDestroyer(Texture texture, Vector2 position, float radius) {
        super(new CircleObject(texture, position, radius));
        initialize();
        refreshExternalDependencies();
    }

    @Override
    protected void refreshExternalDependencies() {
        super.refreshExternalDependencies();
        planetCenter = Fin.planetCenter;
    }

    public PlanetDestroyer(Texture texture, float radius) {
        super(new CircleObject(texture, new Vector2(Fin.WIDTH / 2.0f, Fin.HEIGHT * 0.7f), radius));
        //initialize();
        //mass = 1.0f;
        //speed = new Vector2(0, 0.1f);
    }
    public PlanetDestroyer(Texture texture, Vector2 position, float radius, float mass) {
            super(new CircleObject(texture, position, radius));
            this.mass = mass;
    }

    protected void initialize() {
        super.initialize();
        mass = 1.0f;
        speed = new Vector2(0, -0.02f);
    }
    //endregion

    @Override
    public void update() {
        super.update();
        updateSpeed();
        updatePosition();
    }
    protected void updateSpeed() {


        Vector2 force = SpecialMath.subVector(getPosition(), planetCenter);
        force.nor();
        this.speed = SpecialMath.sumVector(speed, force.scl(Gravity.acceleration));
    }
    protected void updatePosition(){
        setPosition(SpecialMath.sumVector(gameObject.getPosition(), speed));
    }


    public void interaction() {
        speed.rotate(angleOfHeat);
    }
}
