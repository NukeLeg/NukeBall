package com.sir.black.Screens.SupportState;

/**
 * Created by NoOne on 21.06.2018.
 */

public class LocalCounter {
    //region fields
    /**
     * Локальний лічильник
     */
    int localCounter; // Локальний лічильник
    //endregion

    //region get/set
    public int getLocalCounter() {
        return localCounter;
    }

    public void setLocalCounter(int localCounter) {
        this.localCounter = localCounter;
    }
    //endregion

    //region construct
    public LocalCounter(){
        localCounter = 0;
    }
    //endregion

    //region external

    /**
     * збільшення лічильника
     */
    public void update(){
        localCounter++;
    }
    //endregion
}
