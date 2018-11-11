package com.sir.black.Tools.Character;

import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;

public interface Gravity {
    float acceleration = Fin.acceleration;
    void updateSpeed();
    void influence();
}
