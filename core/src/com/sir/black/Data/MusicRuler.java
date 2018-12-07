package com.sir.black.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
public final class MusicRuler {
    private static Music[] mainTheme;
    private MusicRuler() {
    }
    public static MusicRuler initialize(){
        return new MusicRuler();
    }
    static{
        mainTheme = new Music[]{Gdx.audio.newMusic(Gdx.files.internal("Music/game_of_thrones_main_theme.mp3"))};

    }
    public static void play(){
        mainTheme[0].play();
    }
    public static void dispose(){
        for( Music sound : mainTheme){
            if(sound != null){
                sound.dispose();
            }
        }
    }
    public static boolean isPlaying(){
        return mainTheme[0].isPlaying();
    }
    public static void switchMusic(){
        if(mainTheme[0].isPlaying()) mainTheme[0].pause();
        else mainTheme[0].play();
    }
}
