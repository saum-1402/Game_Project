package com.badlogic.mygame;

import Scenes.HUD;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.tools.javac.util.Name;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import java.awt.*;

import static com.badlogic.gdx.Gdx.app;
import static java.awt.Color.black;
import static java.awt.Color.blue;


public class MainMenu1 extends ApplicationAdapter implements Screen {
    public MyGame game;
    public Stage stage;
    private OrthographicCamera game_camera;
    private Viewport game_port;
    private HUD hud;
    private Texture tank_image;

    private Texture surface;
    private Texture background_image;
    SpriteBatch spriteBatch = new SpriteBatch();
    private TextureAtlas atlas;
    private TextButton buttonplay,buttonExit;

    private Label outputLabel;
    //    Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
    private Skin skin;

    public MainMenu1(MyGame game){
        this.game = game;
        game_camera=new OrthographicCamera();



        //image assets
        background_image = new Texture(Gdx.files.internal("badlogic.jpg"));
        tank_image=new Texture(Gdx.files.internal("tank_image.png"));
        //add surface

        //hud
        hud = new HUD(game.batch);

        //add music

        //for views
        game_port=new ScreenViewport(game_camera);//different configs for game screen
        // game_port=new StretchViewport(800,400,game_camera);                 //different configs for game screen
        //game_port=new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,game_camera);  //different configs for game screen



    }

    public MainMenu1() {
    }

    @Override
    public void create () {

    }
    @Override
    public void show() {
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
//        stage = new Stage(new ScreenViewport());
        stage = new Stage(new ScreenViewport());
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        Button button2 = new TextButton("Start Playing",mySkin,"small");
        button2.setSize(col_width*4,row_height);
        button2.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*3);
        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("i m up");
//                game.setScreen(new PlayScreen(game));
//                outputLabel.setText("Press a Button");
                return;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                outputLabel.setText("Pressed Text Button");
                game.setScreen(new PlayScreen(game));
                return true;
            }
        });
        stage.addActor(button2);
    }

    @Override
    public void render(float delta) {
        game_camera.update();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        game.batch.begin();
        game.batch.draw(background_image, 0,0, MyGame.V_WIDTH, MyGame.V_HEIGHT);



//        game.batch.draw(surface, 0,0, MyGame.V_WIDTH, MyGame.V_HEIGHT);   //surface hasn't been added

        game.font.draw(game.batch, "Tank Wars!", MyGame.V_WIDTH/3, MyGame.V_HEIGHT/3);
//        game.font.draw(game.batch, "Click anywhere to begin!", MyGame.V_WIDTH/3, 2*MyGame.V_HEIGHT/3);
        stage.act();
        stage.draw();

        game.batch.end();
        Gdx.input.setInputProcessor(stage);

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
        stage.dispose();

    }
}


