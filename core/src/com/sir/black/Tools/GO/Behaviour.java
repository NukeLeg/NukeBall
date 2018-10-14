package com.sir.black.Tools.GO;

/**
 * Визначає поводження частинки, як вона буде рухатися, крутится, змінювати розмір, змінювати колір
 * 16.01.18
 */

public class Behaviour {
    //region construct
    /**
     * Стандартний набір змін у обєкта
     */
    public Behaviour() { }
    //endregion

    /**
     * Скопіювати обєкт
     * @return копія обєкта цього класу
     */
    public Behaviour cpy(){
        Behaviour behaviour = new Behaviour();
        return behaviour;
    }


    /*//region external
    public void update(Condition condition, Vector2 position, Vector2 scale,
                       Color color, Vector2 rotation, Vector2 zero) {
        updateTime(condition);
        updatePosition(condition, position);
        updatePositionZW(condition);
        updateScale(condition, scale);
        updateColorRG_updateColorBA(condition, color);
        updateRotation(condition, rotation);
        updatePartOfPictureAA(condition);
        updatePartOfPictureZZ(condition);
        updateZero(condition, zero);
    }
    //endregion

    //region internal
    *//**
     * Обновлення часу життя частинки, власне таймер його життя
     *//*
    protected void updateTime(Condition condition) { if (this.time!= null) this.time.update(condition, null, null); }
    *//**
     * Обновлення позиції
     * @param condition Стан обєтка
     * @param position позиція обєкта
     *//*
    protected void updatePosition(Condition condition, Vector2 position) { if (this.position!= null) this.position.update(condition, position, time); }
    *//**
     * Обновлення третьої координати і часу
     * @param condition Стан обєтка
     *//*
    protected void updatePositionZW(Condition condition) { if (this.positionZW!= null) this.positionZW.update(condition, null, time); }
    *//**
     * Обновлення маштабу
     * @param condition Стан обєтка
     * @param scale маштаб обєкта
     *//*
    protected void updateScale(Condition condition, Vector2 scale) { if (this.scale!= null) this.scale.update(condition, scale, time);}
    *//**
     * Обновлення кольору
     * @param condition Стан обєтка
     * @param color колір обєкта
     *//*
    protected void updateColorRG_updateColorBA(Condition condition, Color color) {
        Vector2 RG = new Vector2(color.r, color.g);
        Vector2 BA = new Vector2(color.b, color.a);
        if (this.colorRG!= null)
            this.colorRG.update(condition, RG, time);
        if (this.colorBA!= null)
            this.colorBA.update(condition, BA, time);
        color.r = RG.x;
        color.g = RG.y;
        color.b = BA.x;
        color.a = BA.y;
    }
    *//**
     * Обовлення кручення обєкта
     * @param condition Стан обєтка
     * @param rotation кручення обєктів
     *//*
    protected void updateRotation(Condition condition, Vector2 rotation) { if (this.rotation!= null) this.rotation.update(condition, rotation, time); }
    *//**
     * Обновлення рамок частин картинки зліва знизу
     * @param condition Стан обєтка
     *//*
    protected void updatePartOfPictureAA(Condition condition) { if (this.partOfPictureAA!= null) this.partOfPictureAA.update(condition, null, time); }
    *//**
     * Обновлення рамок частин картинки зправа зверху
     * @param condition Стан обєтка
     *//*
    protected void updatePartOfPictureZZ(Condition condition) { if (this.partOfPictureZZ!= null) this.partOfPictureZZ.update(condition, null, time); }
    *//**
     * Обновлення додаткового параметра
     * @param condition стан обєкта
     * @param zero Спеціальний вектор
     *//*
    protected void updateZero(Condition condition, Vector2 zero) { if (this.zero!= null) this.zero.update(condition, null, time); }
    //endregion*/
}
