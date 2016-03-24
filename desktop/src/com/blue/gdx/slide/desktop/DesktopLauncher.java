package com.blue.gdx.slide.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blue.gdx.slide.SlideGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 720 / 2;
		config.height = 1280 / 2;
		new LwjglApplication(new SlideGame(), config);
	}
}
