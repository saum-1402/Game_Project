package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.mygame.MyGame;

public class Tank extends Sprite
{
    public World world;
    public Body b2body;
    public static final float PPM=100;
    public Texture Texture;

    @Override
    public com.badlogic.gdx.graphics.Texture getTexture() {
        return Texture;
    }

    public Tank(World world)
    {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(32/PPM,32/PPM);
        b2body = world.createBody(bdef);
        this.world = world;
        Texture=new Texture(Gdx.files.internal("tank_image.png"));
//        this.b2body=new Body();
    }
    public void defineTank()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/MyGame.PPM ,32/MyGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/MyGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);

        fdef.shape = shape;
        b2body.createFixture(fdef);



    }

    public void update(float dt){

        // time is up : too late mario dies T_T
        // the !isDead() method is used to prevent multiple invocation
        // of "die music" and jumping
        // there is probably better ways to do that but it works for now.
//        if (screen.getHud().isTimeUp() && !isDead()) {
//            die();
//        }

//        //update our sprite to correspond with the position of our Box2D body
//        if(marioIsBig)
//            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2 - 6 / MyGame.PPM);
//        else
//            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
//        //update sprite with the correct frame depending on marios current action
//        setRegion(getFrame(dt));
//        if(timeToDefineBigMario)
//            defineBigMario();
//        if(timeToRedefineMario)
//            redefineMario();
//
//        for(FireBall  ball : fireballs) {
//            ball.update(dt);
//            if(ball.isDestroyed())
//                fireballs.removeValue(ball, true);
//        }

    }
}
