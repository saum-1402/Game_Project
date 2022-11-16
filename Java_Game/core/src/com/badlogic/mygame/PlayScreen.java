package com.badlogic.mygame;

import Scenes.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Input.Keys;


public class PlayScreen implements Screen {
    final MyGame game;
    private OrthographicCamera game_camera;
    private Viewport game_port;
    private HUD hud;
    private Texture background_image;
    SpriteBatch spriteBatch = new SpriteBatch();
    public PlayScreen(MyGame game){
        this.game = game;
        game_camera=new OrthographicCamera();

        background_image = new Texture(Gdx.files.internal("loadingbackgroundimage.jpg"));
//      game_port=new ScreenViewport(game_camera);                      //different configs for game screen
//      game_port=new StretchViewport(800,400,game_camera);                 //different configs for game screen
        game_port=new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,game_camera);  //different configs for game screen
        hud = new HUD(game.batch);


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        game.batch.begin();
        game.batch.draw(background_image, 0,0, MyGame.V_WIDTH, MyGame.V_HEIGHT);
        game.font.draw(game.batch, "Welcome to Drop!", MyGame.V_WIDTH/3, MyGame.V_HEIGHT/3);
        game.font.draw(game.batch, "Click anywhere to begin!", MyGame.V_WIDTH/3, 2*MyGame.V_HEIGHT/3);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        game_port.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
