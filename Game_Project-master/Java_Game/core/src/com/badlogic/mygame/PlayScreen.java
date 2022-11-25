package com.badlogic.mygame;

import Scenes.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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


public class PlayScreen implements Screen {
    final MyGame game;
    private OrthographicCamera game_camera;
    private Viewport game_port;
    private HUD hud;
    public Stage stage;

    private Texture background_texture;
    public PlayScreen(MyGame game){
        this.game = game;
        game_camera=new OrthographicCamera();

        //image assets
        background_texture = new Texture(Gdx.files.internal("map_selection_menu.jpg"));
//        tank_image=new Texture(Gdx.files.internal("tank_image.png"));           //tank ka texture
        //add surface

        //hud
        hud = new HUD(game.batch);

        //add music

        //for views
        // game_port=new ScreenViewport(game_camera);                      //different configs for game screen
        // game_port=new StretchViewport(800,400,game_camera);                 //different configs for game screen
        game_port=new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,game_camera);  //different configs for game screen


    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        int Help_Guides = 12;
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        Button button2 = new TextButton("Pearl", mySkin, "small");
        button2.setSize(col_width * 7/2, row_height);
        button2.setPosition(col_width * 5, Gdx.graphics.getHeight() - row_height * 7);
        button2.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("i m up");
                return;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                outputLabel.setText("Pressed Text Button");
//                game.setScreen(new PlayScreen(game));
                game.setScreen(new tank_select_screen(game));
                return true;

            }
        });
        stage.addActor(button2);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game_camera.update();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        game.batch.begin();

        game.batch.draw(background_texture, 0,0, MyGame.V_WIDTH, MyGame.V_HEIGHT);
//        game.batch.draw(tank_image, 120,10, 30, 30);
        game.batch.end();
        stage.act();
        stage.draw();
        Gdx.input.setInputProcessor(stage);

//        if (Gdx.input.isTouched()) {
//            game.setScreen(new PlayScreen(game));
//            dispose();
//        }

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
    }
}
