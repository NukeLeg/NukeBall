package com.sir.black.Common;

import com.badlogic.gdx.ApplicationAdapter;
import com.sir.black.Data.Fin;
import com.sir.black.Data.Song;
import com.sir.black.Data.Textures;
import com.sir.black.Screens.GameState.GameState;
import com.sir.black.Screens.State;
import com.sir.black.Screens.FlashState.FlashState;
import com.sir.black.Data.MusicRuler;

/**
 * Головний клас гри
 * 01.01.18
 */

public class MainGame extends ApplicationAdapter {
	//region fields
	/**
	 * Всередині всі змінні
	 */
	Fin fin; // Всередині всі змінні
	/**
	 * Всі текстури
	 */
	Textures textures; // Всі текстури
	/**
	 * Вся музика
	 */
	Song song; // Вся музика
    MusicRuler musicRuler; //music
	/**
	 * Загальні події
	 */
	Activity activity; // Загальні події
	/**
	 * Початковий скрін меню
	 */
	State state; // Початковий скрін меню
	/**
	 * Регулює переходи між менюшками
	 */
	public GameStateManager gameStateManager; // Регулює переходи між менюшками
	//endregion

	//region external
	/**
	 * Створюємо гру. Викликається один раз
 	 */
	@Override
	public void create () {
		fin = new Fin();
		textures = new Textures();
		song = new Song();
        musicRuler = MusicRuler.initialize();
		activity = new Activity();
		gameStateManager = new GameStateManager();

		state = new FlashState(gameStateManager); // Задати початковий скрін гри
		gameStateManager.push(state); // встановлюємо перший екран
		MusicRuler.play();
	}

	/**
	 * Обновлення гри кожен момент часу
	 */
	@Override
	public void render () {
		super.render(); /**З батьківського щось там*/
		update(); // Обновка
		draw(); // Промальовка
	}

	@Override
	public void dispose () { textures.dispose(); song.dispose(); MusicRuler.dispose(); }
	//endregion

	//region internal
	protected void update() {
		activity.update();
		gameStateManager.update();
	}

	protected void draw() {
		activity.draw();
		gameStateManager.draw();
	}
	//endregion
}