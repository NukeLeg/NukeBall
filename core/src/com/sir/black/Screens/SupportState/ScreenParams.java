package com.sir.black.Screens.SupportState;

import com.sir.black.Data.Fin;

/**
 * Created by NoOne on 21.06.2018.
 */

public class ScreenParams {
    /**
     * Поточна ширина екрана
     */
    public int WIDTH; // Поточна ширина екрана
    /**
     * Поточна висота екрана
     */
    public int HEIGHT; // Поточна висота екрана
    /**
     * Оригінальна ширина екрана
     */
    public int HDWidth; // Оригінальна ширина екрана
    /**
     * Оригінальна висота екрана
     */
    public int HDHeight; // Оригінальна висота екрана


    public int getWIDTH() { return WIDTH; }
    public int getHEIGHT() { return HEIGHT; }
    public int getHDWidth() { return HDWidth; }
    public int getHDHeight() { return HDHeight; }
    public void setWIDTH(int WIDTH) { this.WIDTH = WIDTH; }
    public void setHEIGHT(int HEIGHT) { this.HEIGHT = HEIGHT; }
    public void setHDWidth(int HDWidth) { this.HDWidth = HDWidth; }
    public void setHDHeight(int HDHeight) { this.HDHeight = HDHeight; }

    public ScreenParams() {  archive(); }

    /**
     * запис параметрів із архіву
     */
    protected void archive(){
        this.WIDTH = Fin.WIDTH;
        this.HEIGHT = Fin.HEIGHT;
        this.HDWidth = Fin.HDWidth;
        this.HDHeight = Fin.HDHeight;
    }

    public void updateParams(){
        archive();
    }

    public void update() { }
}
