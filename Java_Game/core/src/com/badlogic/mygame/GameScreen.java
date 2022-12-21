package com.badlogic.mygame;
//  made it for collide for current position of tank
//  this is pearl
//sanskar ka edit

import Scenes.HUD;
import Sprites.Tank;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
// generate a terrain for tank game


public class GameScreen implements Screen {

    //box2d componenst
    private World world;
    private float health2=100;
    private float health1=100;
    private Box2DDebugRenderer b2dr;

    final MyGame game;
    Rectangle tank;
    Rectangle tank2;

    Rectangle missile1;
    Rectangle missile1R;
    private OrthographicCamera game_camera;
    private Viewport game_port;
    private HUD hud;
    public Stage stage;
    private Texture tank_image;     //texture declaration
    private Texture tank_image2;
    private Texture missile;
    private Texture missileR;
    private Texture healthbar;
    //    private Texture tank_image2;
    private Texture surface;
    private Texture background_texture;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private  Texture healthbar2;
    private  Texture healthbar2R;
    private  Texture healthbar2R1;
    private  Texture healthbar2R2;
    private  Texture healthbar2R3;

    private  Texture healthbar2L1;
    private  Texture healthbar2L2;
    private  Texture healthbar2L3;
    private  Texture healthbar2L4;

    private  Texture healthbar2R4;

    private Tank player;

    double xbulletSpeed=1.5;
    double ybulletSpeed=1.5;
    double xbulletSpeedR=1.5;
    double ybulletSpeedR=1.5;
    int count=1;

    int h1=1;
    int hl=1;
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
        tank.x = 60;
        tank2=new Rectangle();
        tank2.x=260;
        missile1= new Rectangle();
        missile1.x=180;
        missile1.y=180;
        missile1R=new Rectangle();
        missile1R.x=180;
        missile1R.y=180;
//        final;

                // center the bucket horizontally
//        tank.y = 20; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
//        tank.width = 64;
//        tank.height = 64;

        game_camera=new OrthographicCamera();
        //for views
        // game_port=new ScreenViewport(game_camera);                      //different configs for game screen
        // game_port=new StretchViewport(800,400,game_camera);                 //different configs for game screen
        game_port=new FitViewport(MyGame.V_WIDTH/MyGame.PPM,MyGame.V_HEIGHT/MyGame.PPM,game_camera);  //different configs for game screen


        //image assets
        background_texture = new Texture(Gdx.files.internal("game_screen.jpg"));
        missile=new Texture(Gdx.files.internal("ball.png"));
        missileR=new Texture(Gdx.files.internal("ball.png"));
        tank_image=new Texture(Gdx.files.internal("tank_image.png"));
        tank_image2=new Texture(Gdx.files.internal("tank_image_2.png"));  //tank ka texture
        healthbar=new Texture(Gdx.files.internal("healthbar.png"));

        healthbar2R1=new Texture(Gdx.files.internal("healthbar.png"));
        healthbar2R2=new Texture(Gdx.files.internal("healthbar2.png"));
        healthbar2R3=new Texture(Gdx.files.internal("healthbar3.png"));
        healthbar2R4=new Texture(Gdx.files.internal("healthbar4.png"));

        healthbar2L1=new Texture(Gdx.files.internal("healthbar.png"));
        healthbar2L2=new Texture(Gdx.files.internal("healthbar2.png"));
        healthbar2L3=new Texture(Gdx.files.internal("healthbar3.png"));
        healthbar2L4=new Texture(Gdx.files.internal("healthbar4.png"));

        healthbar2=new Texture(Gdx.files.internal("healthbar2.png"));
//        healthbar2R=new Texture(Gdx.files.internal("healthbar2.png"));
        //add surface

        //hud
        hud = new HUD(game.batch);

        mapLoader=new TmxMapLoader();
        map=mapLoader.load("ground.tmx");
        renderer=new OrthogonalTiledMapRenderer(map,1/MyGame.PPM);

        world = new World(new Vector2(0, -10/MyGame.PPM), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
//        b2dr.SHAPE_STATIC.set(1,1,1,1);
        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MyGame.PPM, (rect.getY() + rect.getHeight() / 2) / MyGame.PPM);
//            bdef.position.set(rect.getX() + rect.getWidth() / 2,rect.getY() + rect.getHeight() / 2);
//
            body = world.createBody(bdef);

//            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            shape.setAsBox(rect.getWidth() / 2 / MyGame.PPM, rect.getHeight() / 2 / MyGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        World world1 = new World(new Vector2(2, -10), true);
        player=new Tank(world);




            game_camera.position.set(game_port.getWorldWidth()/2,game_port.getWorldHeight()/2,0);




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
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2){
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2){
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);}
    }
    public void update(float delta){
        handleInput(delta);
//        world.step(1/60f,6,2);
//        game_camera.position.x=player.b2body.getPosition().x;
        player.update(delta);
//        game_camera.update();
        renderer.setView(game_camera);
    }

    @Override
    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(0, 0, 0, 1);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



//        createBox(0,0,100,100);

        game.batch.begin();
//        int x=60;
        game.batch.draw(background_texture, 0,0, MyGame.V_WIDTH, MyGame.V_HEIGHT);
        game.batch.draw(tank_image, tank.x,7, 70, 70);
        game.batch.draw(tank_image2, tank2.x,15, 60, 60);

        game.batch.draw(missile, missile1.x,missile1.y, 10, 12);
        game.batch.draw(missileR, missile1R.x,missile1R.y, 10, 12);

        Texture arr[]= new Texture[4];
        arr[0]=healthbar2R1;
        arr[1]=healthbar2R2;
        arr[2]=healthbar2R3;
        arr[3]=healthbar2R4;

        ArrayList<Integer> c= new ArrayList<>();
        for(int i=0;i<4;i++){
            c.add(i);
        }
        Iterator iter=c.iterator();
        while (iter.hasNext()){
            game.batch.draw(arr[(int)iter.next()], 280,180, 100, 12);
        }



//        if(h1==1){
//            game.batch.draw(healthbar2R1, 280,180, 100, 12);
//        }
//        if(h1==2){
//            game.batch.draw(healthbar2R2, 280,180, 100, 12);
//        }
//        if(h1==3){
//            game.batch.draw(healthbar2R3, 280,180, 100, 12);
//        }
        if(h1==4){
//            game.batch.draw(healthbar2R4, 280,180, 100, 12);
            background_texture.dispose();
            stage.dispose();
            surface.dispose();
        }

        if(hl==1){
            game.batch.draw(healthbar2L1, 42 ,180, 100, 12);
        }
        if(hl==2){
            game.batch.draw(healthbar2L2, 42 ,180, 100, 12);
        }
        if(hl==3){
            game.batch.draw(healthbar2L3, 42 ,180, 100, 12);
        }
        if(hl==4){
            game.batch.draw(healthbar2L4, 42 ,180, 100, 12);
             background_texture.dispose();
            stage.dispose();
            surface.dispose();

        }
//        game.batch.draw(healthbar,42 ,180, 100, 12);
//        game.batch.draw(healthbarR, 280,180, 100, 12);
//        createBox(0,0,100,100);
//        player.draw(game.batch);
//        game.batch.draw(player.getTexture(),player.b2body.getPosition().x,player.b2body.getPosition().y,40,40);
        game.batch.end();

        renderer.render();
        b2dr.render(world,game_camera.combined);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

//        double xbulletSpeed=1;
//        double ybulletSpeed=1;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            tank.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(tank.x==tank2.x-40){
                tank.x+=1;
                tank2.x+=1;
            }
            else{
                tank.x+=1;
            }
        }
        if (tank.x < 0)
            tank.x = 0;
        if (tank.x> tank2.x-40)
            tank.x=tank2.x-40;

        if (Gdx.input.isKeyPressed(Input.Keys.A))
            tank2.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            tank2.x += 1;
        if (tank2.x < 0)
            tank2.x = 0;
        if (tank2.x> 330)
            tank2.x=330;
        if(tank2.x <tank.x+30)
            tank2.x=tank.x+30;
//        if(missile1.y<2){
//            count++;
//        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            System.out.println(count);

            if(count%2==1) {
                missile1.x = tank.x + 30;
                missile1.y = tank.y;
                ybulletSpeed = 3;
                xbulletSpeed = 2;
                count++;
//            count=0;}
            }
            else{
                missile1R.x=tank2.x ;
                missile1R.y=tank2.y;
                xbulletSpeedR=-2;
                ybulletSpeedR=3;
                count++;
//                count=1;
            }

            }
//        int x=1;
        ybulletSpeed-=0.05;
        ybulletSpeedR-=0.05;
        if (missile1.y<200) {
            missile1.y += (float)(ybulletSpeed);
        }
        if (missile1R.y<200) {
            missile1R.y += (float)(ybulletSpeed);
        }
//        if(missile1.y>200){
//            ybulletSpeed=-0.3;}

//        if (missile1.x<300)
        missile1.x+=xbulletSpeed;
        missile1R.x+=xbulletSpeedR;
        if(missile1.x>tank2.x && missile1.x<tank2.x+60 && missile1.y>10 && missile1.y<70){
            missile1.x=400;
            h1+=1;
//            healthbarR.dispose();
//            game.batch.begin();
//            game.batch.draw(healthbarR, 280,180, 100, 12);
//            game.batch.end();
//            health2-=20;
        }

        if(missile1R.x>tank.x && missile1R.x<tank.x+60 && missile1R.y>10 && missile1R.y<60){
            missile1R.x=400;
//            h1+=1;
            hl+=1;
//            healthbarR.dispose();
//            game.batch.begin();
//            game.batch.draw(healthbarR, 280,180, 100, 12);
//            game.batch.end();
//            health2-=20;
        }


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
