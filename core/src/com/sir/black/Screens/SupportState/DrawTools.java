package com.sir.black.Screens.SupportState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Textures;
import com.sir.black.Tools.GO.LayerRuler;
import com.sir.black.Tools.Menu.Menu;

/**
 * Created by NoOne on 21.06.2018.
 */

public class DrawTools {
    //region fields
    /**
     * Засіб для промальовки послойно
     */
    protected LayerRuler layerRuler; // Засіб для промальовки послойно
    /**
     * Засіб для промальовки 2D
     */
    protected SpriteBatch spriteBatch; // Засіб для промальовки 2D
    /**
     * Засіб для промальовки 3D
     */
    protected ModelBatch modelBatch; // Засіб для промальовки 3D
    //endregion

    //region construct
    public DrawTools(){
        archive();
    }

    protected void archive(){
        this.layerRuler = Fin.layerRuler;
        this.spriteBatch = Fin.spriteBatch;
        modelBatch = new ModelBatch();
    }
    //endregion


    //region external
    public void update(){

    }

    /**
     * промальовка 2D шару меню та Checker шару
     * @param menu шар меню
     * @param camera2DMenu камера для промальовки меню 2D
     */
    public void draw(Menu menu, Camera2D camera2DMenu){
        if (camera2DMenu != null) {
            spriteBatch.setProjectionMatrix(camera2DMenu.getCamera().combined);

            if (menu != null) {
                spriteBatch.begin();
                menu.draw(spriteBatch); // Промальовує меню
                spriteBatch.end();
            }
        }
    }


    /**
     * промальовує 2D ою'єкти
     * @param map карта 2D об'єктів яку потрібно промалювати
     * @param camera2D камера
     */
    public void draw(Map map, Camera2D camera2D){
        if (camera2D != null) {
            spriteBatch.setProjectionMatrix(camera2D.getCamera().combined);

            if (map != null) {
                spriteBatch.begin();
                map.draw(); // Вставляє в стек все що треба промалювати
                layerRuler.drawBatch(); // Промальовує стек промальовки layerRuler
                spriteBatch.end();
            }
        }
    }


    //endregion
}
