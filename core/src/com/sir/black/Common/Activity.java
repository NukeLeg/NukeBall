package com.sir.black.Common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.sir.black.Data.Fin;

/**
 * Created by NoOne on 10.09.2018.
 */

public class Activity {
    //region external
    public void update(){
        common();

        counter();
        FPS();
    }

    public void draw(){
        clearColor();
    }
    //region internal

    //endregion
    /**
     * Певні загальні дії
     */
    private static void common() { }

    /**
     * Звичайний лічильник
     */
    private void counter()
    {
        Fin.counter++; /** лічильник */
    }
    /**
     * Визначити ФПС
     */
    private void FPS() {
        Fin.FPS = Gdx.graphics.getFramesPerSecond(); // Фіпіес
    }

    /**
     * Затираємо кольори і задаємо колір
     */
    private void clearColor() {
        Gdx.gl.glClearColor(Fin.backGround.r, Fin.backGround.g, Fin.backGround.b, Fin.backGround.a);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT
                //				|(Gdx.graphics.getBufferFormat()
                //				.coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0)
        );
    }
    //endregion
}
