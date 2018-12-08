package com.sir.black.Tools.Character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.InitialObject.GameObject;

/**
 * Керувач промальокою, сортує слої промальовки
 * 09.01.18
 */

public class LayerRuler {
    /**
     * Якщо вийшли за границі кількості можливих елементів промальовки
     */
    public class OutOfMaxNumberException extends Exception { public OutOfMaxNumberException() { } }

    //region fields
    /**
     * Всі поля які треба буде промалювати
     */
    GameObject[] field; // Всі поля які треба буде промалювати
    /**
     * Одне поле для сортування
     */
    GameObject field0; // Одне поле для сортування
    /**
     * Лічильник який показує останній заповнений обєкт
     */
    int counter; // Лічильник який показує останній заповнений обєкт

    /**
     * Спрайт бетч
     */
    SpriteBatch spriteBatch; // Спрайт бетч
    /**
     * Промальовка примітивів
     */
    ShapeRenderer shapeRenderer; // Промальовка примітивів

    /**
     * Максимальний розмір промальовуваних картинок
     */
    int max; // Максимальний розмір промальовуваних картинок
    //endregion

    //region construct/refreshExternalDependencies/default
    /**
     * Для промальовки обєктів послойно
     * @param spriteBatch для звичайної промальовки
     * @param shapeRenderer для промальовки примітивів
     */
    public LayerRuler(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        /// Вхідні параметри
        this.spriteBatch = spriteBatch;
        this.shapeRenderer = shapeRenderer;

        archive(); // Взято з архіва
        defaultValue(); // Значення за замовчуванням
    }

    /**
     * Взято з архіва
     */
    protected void archive(){
        this.max = Fin.layerRuler_maxDrawTextures; // Максимальна кількість обєктів які можна промалювати
    }

    /**
     * Значення за замовчуванням
     */
    protected void defaultValue(){
        this.counter = -1; // Лічильник на мінус -1 бо немає ніяких обєктів
        this.field0 = null; // Зануляємо обєкт для сортування
        this.field = new GameObject[this.max]; // Масив обєктів які будемо промальовувати
    }
    //endregion

    //region external
    /**
     * Додати новий обєкт в стек для промальовки
     * @param GameObject обєкт для промальовки
     * @throws OutOfMaxNumberException генерується якщо вийшли за границі масива обєтків
     */
    public void add(GameObject GameObject) throws OutOfMaxNumberException {
        if (counter < max - 1)
        { // Якщо входить в межі масива то додаємо до масива
            counter++;
            this.field[counter] = GameObject;
        }
        else
        { // Якщо вийшли за межі масива то генеруємо виключення
            throw new OutOfMaxNumberException();
        }
    }

    /**
     * Звичайна промальовка всіх обєктів обєктів
     */
    public void drawBatch() {
        pyramidalSort();
        //sort();
        for (int i = 0; i <= counter; i++) {

            field[i].trueDraw(spriteBatch);
            field[i] = null;
        }
        counter = -1;
        spriteBatch.setColor(Color.WHITE);
    }
    //endregion

    //region saveCode
    // Не використовується
    /**
     * Реальна промальовка всіх обєктів
     */
    /*public void draw() {
        //pyramidalSort();
        sort();
        for (int i = 0; i <= counter; i++) {

            field[i].trueDraw(spriteBatch, shapeRenderer);
            field[i] = null;
        }
        counter = -1;
        spriteBatch.setColorStart(Color.WHITE);
    }*/

    /*public void drawShape() {
        //pyramidalSort();
        sort();
        for (int i = 0; i <= counter; i++) {

            field[i].trueDrawShape(shapeRenderer);
            //field[i] = null;
        }
        //counter = -1;
    }*/
    //endregion

    //region internal
    /**
     * Звичайне сортування вставками
     */
    protected void sort() {
        for (int i = 0; i < counter; i++) {
            for (int j = i+1; j <= counter; j++) {
                if (field[i].getLayer() > field[j].getLayer()) {
                    field0 = field[i];
                    field[i] = field[j];
                    field[j] = field0;
                }
            }
        }
    }

    // region Сортування пірамідою
    /**
     * побудова двійкової кучі (не зростаюче бінарне дерево) із масиву
     */
    void makeHeap() {
        for(int i = 0; i <= counter; i++)
        {
            // починаємо побудову із кінця масиву
            // тобто із останнього доданого елементу в дерево
            int index = i;
            while(index != 0){
                // знаходимо індекс батьківського запису
                int parentIndex = (index - 1) / 2;
                //рівняємо доченьку із батенькою
                if(field[index].getLayer() <= field[parentIndex].getLayer())
                    break;

                //скидаємо отца свого із трону і займаємо його місце
                field0 = field[index];
                field[index] = field[parentIndex];
                field[parentIndex] = field0;

                //скіпетр переходить до нас
                index = parentIndex;
            }
        }
    }

    /**
     * відновлює бінарне дерево після видалення з нього елементу
     */
    public void restoreHeap(int counter) {
        //позиція вузла
        int index = 0;
        while(true){
            // індекси дочірніх записів у кучі (бінарному дереві)
            int childIndex1 = 2*index + 1;
            int childIndex2 = 2*index + 2;

            // якщо дочки випали із масиву то йдемо до батька
            if (childIndex1 > counter) childIndex1 = index;
            if (childIndex2 > counter) childIndex2 = index;

            // властивість бінарного дерева або кучі виконана виходимо
            if(field[childIndex1].getLayer() <= field[index].getLayer() &&
                    field[childIndex2].getLayer() <= field[index].getLayer())
                break;

            // індекс більшого дочірнього запису
            int largerChild;
            if(field[childIndex1].getLayer() > field[childIndex2].getLayer())
                largerChild = childIndex1;
            else
                largerChild = childIndex2;

            // міняємо місцями батька і найбільшу дочку
            field0 = field[index];
            field[index] = field[largerChild];
            field[largerChild] = field0;

            // переходимо до дочки
            // спускаємо індекс нижче по масиву для подальшої перевірки
            index = largerChild;
        }
    }

    /**
     * сортування пірамідкою або кучею
     */
    public void pyramidalSort(){
        // будуємо бінарне дерево
        makeHeap();
        // розмір бінарного дерева першопочатково рівний розміру масива
        int heapSize = counter;

        for (int i = counter; i > 0; i--){
            // міняємо місцями перший(найбільший) елемент і останній
            field0 = field[0];
            field[0] = field[i];
            field[i] = field0;

            // останній нам сортувати не потрібно тому його не чіпаємо
            heapSize--;

            restoreHeap(heapSize);
        }
    }
    // endregion
    //endregion
}
