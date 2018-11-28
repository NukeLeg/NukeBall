package com.sir.black.Tools.Character.Behaviour;

import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.InitialObject.GameObject;
import com.sir.black.Tools.Character.Physics.PhysicalParameters;

/**
 * Визначає поводження частинки, як вона буде рухатися, крутится, змінювати розмір, змінювати колір
 * 16.01.18
 */

public class Behaviour {
    //region construct
    /**
     * Стандартний набір змін у обєкта
     */
    protected float acceleration;
    public Behaviour() {
        initialize();
    }
    //endregion
    protected void initialize(){
        acceleration = 0;
    }
    public void update(PhysicalParameters physParams) {    }
}
