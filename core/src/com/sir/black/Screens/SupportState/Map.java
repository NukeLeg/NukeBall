package com.sir.black.Screens.SupportState;

import com.sir.black.Screens.Mediator.IHaveMediator;
import com.sir.black.Screens.Mediator.Mediator;
import com.sir.black.Tools.Character.Character;
import com.sir.black.Tools.Character.InitialObject.GameObject;

import java.util.LinkedList;

/**
 * Created by NoOne on 21.06.2018.
 */

public class Map implements IHaveMediator {
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
    protected Mediator mediator;
    protected LinkedList<Character> characters;
    //endregion

    //region get/set/mod
    @Override
    public Mediator getMediator() { return mediator; }
    @Override
    public void setMediator(Mediator mediator) { this.mediator = mediator; }
    //endregion

    //region construct
    public Map() {
        refreshExternalDependencies();
        initialize();
        initializeLocation();
    }
    protected void initialize(){
        characters = new java.util.LinkedList<Character>();
    }
    protected void addNewCharacter(Character character){
        characters.add(character);
    }
    protected void initializeLocation(){

    }
    private void refreshExternalDependencies() {    }
    //endregion

    //region external
    /**
     * оновлення стану мапи
     */
    public void update() {
        updateCharacter();
        deleteCharacter();
        collisionCharacter();

        updateMediator();
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
            for (int i = 0; i < characters.size(); i++) {
                try { characters.get(i).update(); }
                catch (Exception e) { }
            }
        }
        catch (Exception e) { }
    }
    protected void deleteCharacter() {
        try {
            for (int i = 0; i < characters.size(); i++) {
                if (isReadyToDelete(characters.get(i)))
                    characters.remove(i);
            }
        } catch (Exception e) { }
    }

    protected void collisionCharacter(){
        try {
            for (int i = 0; i < characters.size(); i++) {
                for (int j = i + 1; j < characters.size(); j++) {
                    if (isExist(characters.get(i)))
                        interaction(i, j);
                }

            }
        }
        catch (Exception e) { }
    }

    protected void interaction(int i, int j){

    }

    protected void drawCharacter() {
        try {
            for (int i = 0; i < characters.size(); i++) {
                try { characters.get(i).draw(); }
                catch (Exception e) { }
            }
        }
        catch (Exception e) { }
    }

    public void updateMediator(){
        if (mediator != null) mediator.notify(this);
    }
    //endregion
}
