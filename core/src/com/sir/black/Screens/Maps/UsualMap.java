package com.sir.black.Screens.Maps;

import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.SupportState.Map;
import com.sir.black.Tools.Character.Character;
import com.sir.black.Tools.Character.Fraction.Fraction;
import com.sir.black.Tools.Character.GameObject.GameObject;

/**
 * Created by NoOne on 10.09.2018.
 */

public class UsualMap extends Map {
    //region fields

    //endregion

    //region construct
    public UsualMap() {
        defaultValueUsual();
        archiveUsual();
        character = new Character[1];
        character[0] = new Character(
                new GameObject(Textures.hitAnimation, new Vector2(100, 100)),
                new Fraction(317, 323,
                        2, new int[]{4,4,4,4}, 0,0,true)
        );
    }
    private void defaultValueUsual() { }
    private void archiveUsual() { }
    //endregion

    //region external
    /**
     * оновлення стану мапи
     */
    public void update() {
        super.update();
    }
    //endregion
}
