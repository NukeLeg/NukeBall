package com.sir.black.Tools.Character.Shader;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.sir.black.Data.Fin;

/**
 * Керування шейдерами, базовий клас для всіх шейдерів
 * 21.01.18
 */

public class ShaderRule {
    //region fields
    /**
     * Активний шейдер
     */
    protected ShaderProgram shaderProgram; // Активний шейдер
    /**
     * Параметри необхіні для цього шейдера
     */
    protected float[] params; // Параметри необхіні для цього шейдера
    /**
     * Засіб для промальовки за допомогою шейдера
     */
    protected SpriteBatch spriteBatch; // Засіб для промальовки за допомогою шейдера
    /**
     * Файли загрузки
     */
    protected Files files; // Файли загрузки
    //endregion

    //region construct/refreshExternalDependencies/default

    // Статичний конструктор для задання не строгої передачі даних шейдеру
    static { ShaderProgram.pedantic = false; }

    /**
     * Звичайний дефолтний шейдер
     */
    public ShaderRule() {
        archive(); // Із архіва
        defaultValue(); // Дефолтні значення
    }

    /**
     * Для клонування обєктів
     * @param shaderRule обєкт який буде клоновано
     */
    public ShaderRule(ShaderRule shaderRule) {
        this.shaderProgram = shaderRule.shaderProgram;
        if (shaderRule.params != null) {
            this.params = new float[shaderRule.params.length];
            for (int i = 0; i < this.params.length; i++) {
                this.params[i] = shaderRule.params[i];
            }
        }
        this.spriteBatch = shaderRule.spriteBatch;
        this.files = shaderRule.files;
    }

    /**
     * Скопіювати параметри
     * @param shaderRule місце звідки буде скопійовано
     */
    public void copyParams(ShaderRule shaderRule) {
        this.shaderProgram = shaderRule.shaderProgram;
        if (shaderRule.params != null) {
            this.params = new float[shaderRule.params.length];
            for (int i = 0; i < this.params.length; i++) {
                this.params[i] = shaderRule.params[i];
            }
        }
        this.spriteBatch = shaderRule.spriteBatch;
        this.files = shaderRule.files;
    }

    /**
     * Взято з архіва
     */
    protected void archive() { this.spriteBatch = Fin.spriteBatch; this.files = Gdx.files; }

    /**
     * Значення за замовчуванням
     */
    protected void defaultValue() {
        //loadShader("Shader/default/default.vert","Shader/default/default.frag"); // Приклад, потім всерівно зануляється
        shaderProgram = null; // Так як це дефолтний шейдер то занулимо
    }

    /**
     * Скопіювати обєкт
     * @return копія цього ж класу
     */
    public ShaderRule cpy(){
        ShaderRule shaderRule = new ShaderRule();
        shaderRule.shaderProgram = this.shaderProgram;
        if (this.params != null) {
            shaderRule.params = new float[this.params.length];
            for (int i = 0; i < shaderRule.params.length; i++) {
                shaderRule.params[i] = this.params[i];
            }
        }
        shaderRule.spriteBatch = this.spriteBatch;
        shaderRule.files = this.files;

        return shaderRule;
    }
    //endregion

    //region external
    /**
     * Обновлення стану шейдера
     */
    public void update() { setInternalParams(); }

    /**
     * Обновлення через посилання параметрів шейдера
     * @return параметри шейдера в одному масиві
     */
    public float[] getParams() { return params; }

    /**
     * Початок запуска шейдера
     */
    public void shaderBegin(){
        spriteBatch.setShader(shaderProgram);
    }

    /**
     * Завершення цього шейдера
     */
    public void shaderEnd(){
        spriteBatch.setShader(null);
    }
    //endregion

    //region internal
    /**
     * Загрузака шейдера
     * @param vert шлях до вершинного шейдера
     * @param frag шлях до фрагментного кольору
     */
    protected void loadShader(String vert, String frag){
        this.shaderProgram = new ShaderProgram(files.internal(vert), files.internal(frag));
    }

    /**
     * Визначити внутрішні параметри шейдера, якщо такі є
     */
    protected void setInternalParams() { } // Визначити внутрішні параметри шейдера, якщо такі є
    //endregion
}
