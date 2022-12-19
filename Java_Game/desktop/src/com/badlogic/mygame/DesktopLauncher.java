package com.badlogic.mygame;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.mygame.MyGame;
import com.badlogic.gdx.physics.box2d.*;


public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(800, 480);
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setTitle("my-game");
		new Lwjgl3Application(new MyGame(), config);
	}
}
