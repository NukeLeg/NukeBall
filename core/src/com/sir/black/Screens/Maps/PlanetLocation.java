package com.sir.black.Screens.Maps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.SupportState.Map;
import com.sir.black.Tools.Character.Character;
import com.sir.black.Tools.Character.PlanetDestroyer;
import com.sir.black.Tools.Character.PlanetObject;
import com.sir.black.Tools.Special.Checker;
import com.sir.black.Tools.Special.Interaction;
import com.sir.black.Tools.Special.SpecialMath;

/**
 * Created by NoOne on 10.09.2018.
 */

public class PlanetLocation extends Map {

    //region fields
    PlanetDestroyer planetDestroyer;
    //endregion

    //region construct
    public PlanetLocation() {

    }
    @Override
    protected void initializeLocation() {
        super.initializeLocation();
        initializeCirclesOfPlanet();
        initializePlanetDestroyer();
    }

    /**
     * creates circles which the planet consists of
     * defines its positions
     */
    protected void initializeCirclesOfPlanet(){
        this.addNewCharacter(new PlanetObject(Textures.circle, Fin.planetCenter, Fin.CentralCircleRadius));
        for(int i = 1; i <= Fin.numberOfLayers; i++){
            float layerRadius = this.layerRadius(Fin.CentralCircleRadius, i, Fin.defaultCircleRadius);
            int numberOfCirclesPerLayer = (int)( Math.PI * layerRadius / Fin.defaultCircleRadius);
            float deltaAngle = (float) (2 * Math.PI / numberOfCirclesPerLayer);
            float angle = 0.0f;
            for(int  j = 0; j < numberOfCirclesPerLayer; j++){
                this.addNewCharacter(
                        new PlanetObject(Textures.circle,
                                Fin.planetCenter,
                                angle,
                                layerRadius,
                                Fin.defaultCircleRadius
                        )
                );
                angle += deltaAngle;
            }
        }
        characters.get(10).setColor(new Color(1,1,0,1));
        characters.get(112).setColor(new Color(0,1,1,1));
        characters.get(104).setColor(new Color(0,1,1,1));
        characters.get(134).setColor(new Color(0,1,1,1));
        characters.get(112).setColor(new Color(0,1,1,1));
        characters.get(101).setColor(new Color(1,0.5f,1,1));
        characters.get(45).setColor(new Color(1,0,1,1));
    }
    protected void initializePlanetDestroyer(){
        planetDestroyer = new PlanetDestroyer(Textures.circle, Fin.defaultCircleRadius);
        addNewCharacter(planetDestroyer);
    }
    private float layerRadius(float centralRadius, int numberOfLayer, float defaultRadius){
        switch (numberOfLayer){
            case 1 : return centralRadius + defaultRadius ;
            default: return centralRadius + (numberOfLayer * 2 - 1) * defaultRadius * 1;
        }
    }

    public void rotatePlanet(Vector2 mousePositionRevert, Vector2 coordinateDeltaVector){
            float angle;
            Vector2 firstVector = SpecialMath.subVector(mousePositionRevert, Fin.planetCenter);
            Vector2 secondVector = SpecialMath.subVector(firstVector, coordinateDeltaVector);
            angle = SpecialMath.angle(firstVector, secondVector);
            rotatePlanet(-angle);
    }
    protected void rotatePlanet(float angle){
        for(Character character : characters){
            character.rotatePlanetObject(angle);
        }
    }

    protected void collideDestroyer(){
        for(int i = 0; i < characters.size() - 1; i++){
            if (Interaction.BallByBall(planetDestroyer.getPosition(), planetDestroyer.getRadius(),
                    characters.get(i).getPosition(), characters.get(i).getRadius())){
                if (characters.get(i).getColor().r == 1
                        && characters.get(i).getColor().g == 1
                        && characters.get(i).getColor().b == 0
                        && characters.get(i).getColor().a == 1
                        ) {
                    characters.get(i).readyToDelete();
                }
                else
                    characters.get(i).setColor(new Color(1,1,0,1));
                planetDestroyer.interaction();
                Checker.update(new Vector2(i, 0), 4, "Yes: ");
            }
        }
    }
    //endregion

    //region external
    /**
     * оновлення стану мапи
     */
    public void update() {
        super.update();
        collideDestroyer();
    }

    @Override
    protected void drawCharacter() {
        super.drawCharacter();
        Checker.drawVector2(new Vector2(0,500));
    }

    //endregion
}