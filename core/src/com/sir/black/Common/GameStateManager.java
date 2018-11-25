package com.sir.black.Common;

import com.sir.black.Screens.State;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * game screen manager (states of game)
 * 03.01.18
 */

public class GameStateManager {
    //region fields
    /**
     * стек ігрових станів, останнім зайшов першим вийшов
     * */
    private Stack<State> states; // стек ігрових станів, останнім зайшов першим вийшов
    //endregion

    //region construct
    /**
     * Створити менеджер який буде керувати зміною станів
     */
    public GameStateManager() {
        this.states = new Stack<State>();
    }
    //endregion

    //region external
    /**
     * оновлює тільки той екран що знаходиться на вершині стеку
     */
    public void update() { updateState(); /**Обновити стан */ }

    /**
     * Додає стан у вершину стеку
     * @param state Стан який тепер буде у вершині стеку
     */
    public void push(State state){
        this.states.push(state);
        states.peek().pauseOff();
    }

    /**
     * видаляє зі стеку останній еклкмент і очищає його ресурси
     * поміщає наступний елемент у вершину стеку
     * @param state новий стан який замінить верхній стан
     */
    public void set(State state){
        // Якщо якийсь стан є то замінити його
        try { this.states.pop(); }
        // Якщо ніякого стану зверху немає то тупо додати новий стан(він стане першим)
        catch (EmptyStackException emptyStackException) { }

        this.states.push(state);
    }

    /**
     * Удалити верхній стан
     */
    public void pop() {
        // Якщо верхній стан існує то видалити його
        try { this.states.pop(); }
        // Якщо верхнього стану немає то нічого не робити
        catch (EmptyStackException emptyStackException) { }
    }

    /**
     * промальвує верхній елемент у стеці
     * */
    public void draw() { drawState(); /**Промалювати даний стан*/ }
    //endregion

    //region internal
    /**
     * Промалювати саме верхній стан елемент
     */
    private void drawState() { this.states.peek().render(); }

    /**
     * Обновити верхній стан
     */
    private void updateState() {
        this.states.peek().update(); // повертає верхній елемен у стеці не видаляючи його
    }
    //endregion
}
