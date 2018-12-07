package com.sir.black.Screens.MenuStateGeneral;

import com.sir.black.Common.GameStateManager;
import com.sir.black.Screens.CongratulationsState.CongratulationsState;
import com.sir.black.Screens.GameOver.GameOver;
import com.sir.black.Screens.GameState.GameState;
import com.sir.black.Screens.MainScreen.MainScreen;
import com.sir.black.Screens.Mediator.IHaveMediator;
import com.sir.black.Screens.Mediator.Mediator;
import com.sir.black.Tools.Menu.Checkbox;

/**
 * All buttons in menu share music and sound chechboxes
 */
public class MenuStateMediator implements Mediator {

    public MenuStateMediator() {
        super();
    }

    @Override
    public void notify(IHaveMediator sender) {

    }
}