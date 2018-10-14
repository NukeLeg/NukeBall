package com.sir.black.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sir.black.Data.Fin;
import com.sir.black.Common.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Fin.WIDTH;
		config.height = Fin.HEIGHT;
		new LwjglApplication(new MainGame(), config);
	}
}
