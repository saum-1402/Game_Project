package com.badlogic.mygame;

import Scenes.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class PlayScreen implements Screen {
    private MyGame game;

    private OrthographicCamera game_camera;
    private Viewport game_port;
    public PlayScreen(MyGame game){
        this.game = game;
        game_camera=new OrthographicCamera();
//      game_port=new ScreenViewport(game_camera);                      //different configs for game screen
//      game_port=new StretchViewport(800,400,game_camera);                 //different configs for game screen
        game_port=new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,game_camera);  //different configs for game screen
        HUD = new HUD(game.batch);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(HUD.stage.getCamera().combined);

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
