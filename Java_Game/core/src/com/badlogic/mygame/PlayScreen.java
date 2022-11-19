package com.badlogic.mygame;

import Scenes.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Input.Keys;

import javax.swing.plaf.TextUI;


public class PlayScreen implements Screen {
    final MyGame game;
    private OrthographicCamera game_camera;
    private Viewport game_port;
    private HUD hud;
    private Texture tank_image;

    private Texture surface;
    private Texture background_texture;
    SpriteBatch spriteBatch = new SpriteBatch();
    public PlayScreen(MyGame game){
        this.game = game;
        game_camera=new OrthographicCamera();

        //image assets
        background_texture = new Texture(Gdx.files.internal("background_texture.jpg"));
        tank_image=new Texture(Gdx.files.internal("tank_image.png"));
        //add surface

        //hud
        hud = new HUD(game.batch);

        //add music

        // game_port=new ScreenViewport(game_camera);                      //different configs for game screen
        // game_port=new StretchViewport(800,400,game_camera);                 //different configs for game screen
        game_port=new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,game_camera);  //different configs for game screen


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game_camera.update();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
//        hud.stage.draw();
        game.batch.begin();

        game.batch.draw(background_texture, 0,0, MyGame.V_WIDTH, MyGame.V_HEIGHT);
        game.batch.draw(tank_image, 120,10, 30, 30);
//        game.batch.draw(surface, 0,0, MyGame.V_WIDTH, MyGame.V_HEIGHT);   //surface hasn't been added

//        game.font.draw(game.batch, "Welcome to Tank Wars!", MyGame.V_WIDTH/3, MyGame.V_HEIGHT/3);
//        game.font.draw(game.batch, "Click anywhere to begin!", MyGame.V_WIDTH/3, 2*MyGame.V_HEIGHT/3);
        game.batch.end();

//        if (Gdx.input.isTouched()) {
//            game.setScreen(new PlayScreen(game));
//            dispose();
//        }
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
//        background_texture.dispose();
//        surface.dispose();
//        tank_image.dispose();

    }
}
