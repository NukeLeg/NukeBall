package com.sir.black.Screens.FlashState;

import com.sir.black.Common.GameStateManager;
import com.sir.black.Screens.MainScreen.MainScreen;
import com.sir.black.Screens.State;

public class FlashState extends State {
    public FlashState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void refreshExternalDependencies() {
        super.refreshExternalDependencies();
    }

    @Override
    protected void create(GameStateManager gameStateManager) {
        super.create(gameStateManager);
    }

    @Override
    protected void firstAction() {
        super.firstAction();
    }

    @Override
    public void update() {
        super.update();
        gameStateManager.push(new MainScreen(gameStateManager));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    protected void handleInput2D() {
        super.handleInput2D();
    }

    @Override
    protected void menuUpdate() {
        super.menuUpdate();
    }

    @Override
    protected void screenParamsUpdate() {
        super.screenParamsUpdate();
    }

    @Override
    protected void inputControlUpdate() {
        super.inputControlUpdate();
    }

    @Override
    protected void localCounterUpdate() {
        super.localCounterUpdate();
    }

    @Override
    protected void cameraUpdate() {
        super.cameraUpdate();
    }

    @Override
    protected void cameraMenuUpdate() {
        super.cameraMenuUpdate();
    }

    @Override
    protected void drawToolsUpdate() {
        super.drawToolsUpdate();
    }

    @Override
    protected void mapUpdate() {
        super.mapUpdate();
    }

    @Override
    protected void eventListener(int i) {
        super.eventListener(i);
    }

    @Override
    protected void drawToolsDraw() {
        super.drawToolsDraw();
    }
}
