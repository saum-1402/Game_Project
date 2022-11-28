package com.badlogic.mygame;

import Scenes.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class map_select_2 implements Screen {
    final MyGame game;
    private OrthographicCamera game_camera;
    private Viewport game_port;
    private HUD hud;
    public Stage stage;
    private Texture tank_image;     //texture declaration
    private Texture tank_image2;
    private Texture healthbar;
//    private Texture tank_image2;
    private Texture surface;
    private Texture background_texture;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    SpriteBatch spriteBatch = new SpriteBatch();
    public map_select_2(MyGame game){
        this.game = game;
        game_camera=new OrthographicCamera();
        //for views
        // game_port=new ScreenViewport(game_camera);                      //different configs for game screen
        // game_port=new StretchViewport(800,400,game_camera);                 //different configs for game screen
        game_port=new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,game_camera);  //different configs for game screen

        //image assets
        background_texture = new Texture(Gdx.files.internal("game_screen.jpg"));
        tank_image=new Texture(Gdx.files.internal("tank_image.png"));
        tank_image2=new Texture(Gdx.files.internal("tank_image_2.png"));  //tank ka texture
        healthbar=new Texture(Gdx.files.internal("healthbar.png"));
        //add surface

        //hud
        hud = new HUD(game.batch);

        mapLoader=new TmxMapLoader();
        map=mapLoader.load("ground.tmx");
        renderer=new OrthogonalTiledMapRenderer(map);

        game_camera.position.set(game_port.getWorldWidth()/2,game_port.getWorldHeight()/2,0);
        //add music



    }


    @Override
    public void show() {

    }

    public void handleInput(float delta){
        if(Gdx.input.isTouched()){
            game_camera.position.x+=100*delta;

        }
    }
    public void update(float delta){
        handleInput(delta);
        game_camera.update();
        renderer.setView(game_camera);
    }

    @Override
    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        game.batch.begin();

        game.batch.draw(background_texture, 0,0, MyGame.V_WIDTH, MyGame.V_HEIGHT);
        game.batch.draw(tank_image, 60,7, 70, 70);
        game.batch.draw(tank_image2, 260,15, 60, 60);
        game.batch.draw(healthbar,42 ,180, 100, 12);
        game.batch.draw(healthbar, 280,180, 100, 12);
        game.batch.end();
        renderer.render();
//        game_camera.update();
//        stage.act();
//        stage.draw();
        // System.out.println(Gdx.input.getX());

//
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
