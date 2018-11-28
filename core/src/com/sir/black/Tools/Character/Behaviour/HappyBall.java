package com.sir.black.Tools.Character.Behaviour;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Character.Physics.PhysicalParameters;

public class HappyBall extends Behaviour {
    private int initialCounter;
    private Vector2 initialPosition;
    public HappyBall(Vector2 initialPosition) {
        super();
        this.initialPosition = new Vector2();
        this.initialPosition.x = initialPosition.x;
        this.initialPosition.y = initialPosition.y;
    }

    @Override
    protected void initialize() {
        super.initialize();
        initialCounter = Fin.counter;
    }
    @Override
    public void update(PhysicalParameters physParams) {
        physParams.setPosition(new Vector2(initialPosition.x,
                initialPosition.y + (float) Math.abs(Math.sin((Fin.counter - initialCounter) / 10.0f)) * 30));
    }
}
