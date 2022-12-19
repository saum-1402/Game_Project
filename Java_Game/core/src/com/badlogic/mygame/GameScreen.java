package com.badlogic.mygame;
//  made it for collide for current position of tank
//  this is pearl

import Scenes.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.Vector2;

// generate a terrain for tank game


public class GameScreen implements Screen {

    //box2d componenst
    private World world;
    private Box2DDebugRenderer b2dr;

    final MyGame game;
    Rectangle tank;
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
    public GameScreen(MyGame game){
        this.game = game;
//        world = new World(new Vector2(0, 0), true);
//        createBox(0,0,100,100);
//        //box2d things

//        b2dr = new Box2DDebugRenderer();
//        BodyDef bdef = new BodyDef();
//        PolygonShape shape = new PolygonShape();
//        FixtureDef fdef = new FixtureDef();
//        Body body;
//
//        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MyGame.PPM, (rect.getY() + rect.getHeight() / 2) / MyGame.PPM);
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox(rect.getWidth() / 2 / MyGame.PPM, rect.getHeight() / 2 / MyGame.PPM);
//            fdef.shape = shape;
//            body.createFixture(fdef);
//        }

        // create a Rectangle to logically represent the tank
        tank = new Rectangle();
        tank.x = 60; // center the bucket horizontally
//        tank.y = 20; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
//        tank.width = 64;
//        tank.height = 64;

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

        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
//        b2dr.SHAPE_STATIC.set(1,1,1,1);
        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MyGame.V_WIDTH, (rect.getY() + rect.getHeight() / 2) / MyGame.V_HEIGHT);
            bdef.position.set(rect.getX() + rect.getWidth() / 2,rect.getY() + rect.getHeight() / 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
//            shape.setAsBox(rect.getWidth() / 2 / MyGame.V_WIDTH, rect.getHeight() / 2 / MyGame.V_HEIGHT);
            fdef.shape = shape;
            body.createFixture(fdef);
        }




            game_camera.position.set(game_port.getWorldWidth()/2,game_port.getWorldHeight()/2,0);
        //add music



    }


    @Override
    public void show() {

    }
    private void createBox(float x, float y, float width, float height) {
        // Create a body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

// Create a body from the definition
        Body body = world.createBody(bodyDef);

// Create a polygon shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

// Create a fixture from the shape
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);

// Dispose of the shape
        shape.dispose();

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

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);


//        createBox(0,0,100,100);

        game.batch.begin();
//        int x=60;
        game.batch.draw(background_texture, 0,0, MyGame.V_WIDTH, MyGame.V_HEIGHT);
        game.batch.draw(tank_image, tank.x,7, 70, 70);
        game.batch.draw(tank_image2, 260,15, 60, 60);
        game.batch.draw(healthbar,42 ,180, 100, 12);
        game.batch.draw(healthbar, 280,180, 100, 12);
//        createBox(0,0,100,100);
        game.batch.end();

        renderer.render();
        b2dr.render(world,game_camera.combined);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            tank.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            tank.x += 1;
        if (tank.x < 0)
            tank.x = 0;
        if (tank.x> 220)
            tank.x=220;


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
