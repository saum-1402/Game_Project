package com.badlogic.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGame extends Game {
	public static final int V_WIDTH=400;
	public static final int V_HEIGHT=208;

	public SpriteBatch batch;
	public BitmapFont font;
//	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.font = new BitmapFont();
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
//		font.dispose();
	}
}
