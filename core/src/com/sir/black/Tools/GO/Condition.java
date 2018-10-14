package com.sir.black.Tools.GO;

/**
 * Стани обєтка
 * 14.01.18
 */
public enum Condition {
    //region fields
    /**
     * Мертвий обєкт
     */
    DEAD, /**Мертвий обєкт*/
    /**
     * Живий обєкт
     */
    LIVE; /**Живий обєкт*/
    //endregion

    public static Condition startCondition() { return LIVE; }
    // this.original(); Вертає порядковий номер одного з енумів
    // valueOf(name); Отримати значення енума по його строковій назві string
    // values(); - Отримати всі можливі назви переліка
    //
}

