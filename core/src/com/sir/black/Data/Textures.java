package com.sir.black.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * Набір всіх загружаємих текстур
 * 07.01.18
 */

public class Textures {
    //region standart
    public static Texture startButton = new Texture("Texture/startButton.png");
    public static Texture pixel = new Texture("Texture/pixel.png");
    public static Texture badlogic = new Texture("Texture/badlogic.jpg");
    public static Texture redX = new Texture("Texture/RedX.png");
    public static Texture optionsButton = new Texture("Texture/optionsButton.jpg");
    public static Texture circle = new Texture("Texture/circle.png");
    public static Texture fox = new Texture("Texture/fox.jpg");
    public static Texture mem = new Texture("Texture/someMem.jpg");

    public static Texture healthHead = new Texture("Texture/Lines/Head.png");
    public static Texture healthHpFrame = new Texture("Texture/Lines/HpFrame.png");
    public static Texture healthHpLev = new Texture("Texture/Lines/HpLev.png");
    //endregion
    //region BitmapFont
    public static FileHandle timeNewRoman32 =  Gdx.files.internal("Font/TimeNewRoman32.fnt");
    public static FileHandle timeNewRoman60 =  Gdx.files.internal("Font/TimeNewRoman60.fnt");
    //endregion

    //region singlePicture
    public static Texture mum = new Texture("Texture/SinglePicture/mum.png");
    public static Texture green = new Texture("Texture/SinglePicture/Green.png");
    public static Texture red = new Texture("Texture/SinglePicture/Red.png");
    //endregion

    //region multiPicture
    public static Texture hitAnimation = new Texture("Texture/Animation/BallHit2.png");
    public static Texture ballFlyAnimation = new Texture("Texture/Animation/BallFlyAnimation.png");
    //endregion
    ///region MenuElements
    public static Texture startButtonTexture = new Texture("Texture/MenuElements/arrow4.png");
    public static Texture continueButtonTexture = new Texture("Texture/MenuElements/arrow2.png");
    public static Texture hexahonal = new Texture("Texture/MenuElements/hexahonal.png");
    public static Texture soundSign = new Texture("Texture/MenuElements/soundSign.png");
    public static Texture pauseSign = new Texture("Texture/MenuElements/pauseSign.png");
    public static Texture octagonal = new Texture("Texture/MenuElements/octagonal.png");
    public static Texture musicSign = new Texture("Texture/MenuElements/musicSign.png");
    public static Texture musicIndicationSign = new Texture("Texture/MenuElements/musicIndicationSign.png");
    public static Texture menuSign = new Texture("Texture/MenuElements/menuSign.png");
    public static Texture restartArrow = new Texture("Texture/MenuElements/arrow2.png");
    // /endregion
    //region multiPicture
    static{
        green.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
    }
    //endregion

    //region external
    public void dispose() {
        pixel.dispose();
        badlogic.dispose();
        redX.dispose();
        healthHead.dispose();
        healthHpFrame.dispose();
        healthHpLev.dispose();
    }
    //endregion
}
