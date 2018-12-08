package com.sir.black.Tools.Special;

import com.badlogic.gdx.math.Vector2;

/**This class contains  some custom math function
 *
 */
public  class SpecialMath {
    /**
     * returns angle between two vectors
     * @param v1 first vector
     * @param v2 second vector
     * @return
     */
    public static float angle(Vector2 v1, Vector2 v2){
        v1 = v1.nor();
        v2 = v2.nor();
        return (float) (Math.atan2(v2.y,v2.x) - Math.atan2(v1.y,v1.x));
    }
    public static Vector2 subVector(Vector2 v1, Vector2 v2){
        return new Vector2(v1.x-v2.x, v1.y-v2.y);
    }
    public static Vector2 sumVector(Vector2 v1, Vector2 v2){
        return new Vector2(v1.x+v2.x, v1.y+v2.y);
    }
    public boolean circlesIntersect(){
        return false;
    }
}
