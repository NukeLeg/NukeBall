package com.sir.black.Screens.SupportState;

import com.sir.black.Data.Fin;
import com.sir.black.Tools.Character.Character;
import com.sir.black.Tools.Character.GameObject.GameObject;

/**
 * Created by NoOne on 21.06.2018.
 */

public class Map {
    //region static
    public static boolean isExist(Character character){
        return (character != null);
    }

    public static boolean isReadyToDelete(Character character) {
        return isExist(character) && character.isReadyToDelete();
    }

    public static boolean isExist(GameObject character){
        return (character != null);
    }

    public static boolean isReadyToDelete(GameObject character) {
        return isExist(character) && character.isReadyToDelete();
    }
    //endregion

    //region fields
    protected Character[] character; // Всі персонажі
    protected int numberOfCharacters; // Кількість персонажів
    //endregion

    //region construct
    public Map() {
        archive();
        defaultValue();
    }

    private void defaultValue(){
        character = new Character[numberOfCharacters];
    }

    private void archive() {
        this.numberOfCharacters = Fin.numberOfCharacters;
    }
    //endregion

    //region external
    /**
     * оновлення стану мапи
     */
    public void update() {
        updateCharacter();
        deleteCharacter();
        collisionCharacter();
    }

    /**
     * промальовка мапи
     */
    public void draw() {
        drawCharacter();
    }
    //endregion

    //region internal
    protected void updateCharacter() {
        try {
            for (int i = 0; i < character.length; i++) {
                try {
                    character[i].update();
                }
                catch (Exception e) { }
            }
        }
        catch (Exception e) { }
    }
    protected void deleteCharacter() {
        try {
            for (int i = 0; i < character.length; i++) {
                if (isReadyToDelete(character[i]))
                    character[i] = null;
            }
        } catch (Exception e) { }
    }

    protected void collisionCharacter(){
        try {
            for (int i = 0; i < character.length; i++) {
                if (isExist(character[i])){
                    for (int j = i + 1; j < character.length; j++) {
                        if (isExist(character[j]))
                            interaction(i, j);
                    }
                }
            }
        }
        catch (Exception e) { }
    }

    protected void interaction(int i, int j){

    }

    protected void drawCharacter() {
        try {
            for (int i = 0; i < character.length; i++) {
                try { character[i].draw(); }
                catch (Exception e) { }
            }
        }
        catch (Exception e) { }
    }
    //endregion
}
