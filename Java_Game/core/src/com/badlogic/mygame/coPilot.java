////make a tank object for a game
//package com.badlogic.mygame;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.utils.ScreenUtils;
//
//class Tank {
//    private Vector2 position;
//    private Vector2 velocity;
//    private TextureRegion tank_image;
//    private Rectangle bounds;
//    private int health;
//    public Tank(float x, float y){
//        position = new Vector2(x,y);
//        velocity = new Vector2(0,0);
//        tank_image = new TextureRegion(new Texture(Gdx.files.internal("tank_image.png")));
//        bounds = new Rectangle(x,y,tank_image.getRegionWidth(),tank_image.getRegionHeight());
//        health = 100;
//    }
//    public void update(float dt){
//        position.add(velocity.cpy().scl(dt));
//        bounds.setPosition(position.x,position.y);
//    }
//    public void render(SpriteBatch batch){
//        batch.draw(tank_image,position.x,position.y);
//    }
//    public void dispose(){
//        tank_image.getTexture().dispose();
//    }
//    public Vector2 getPosition() {
//        return position;
//    }
//    public void setPosition(Vector2 position) {
//        this.position = position;
//    }
//    public Vector2 getVelocity() {
//        return velocity;
//    }
//    public void setVelocity(Vector2 velocity) {
//        this.velocity = velocity;
//    }
//    public TextureRegion getTank_image() {
//        return tank_image;
//    }
//    public void setTank_image(TextureRegion tank_image) {
//        this.tank_image = tank_image;
//    }
//    public Rectangle getBounds() {
//        return bounds;
//    }
//    public void setBounds(Rectangle bounds) {
//        this.bounds = bounds;
//    }
//    public int getHealth() {
//        return health;
//    }
//    public void setHealth(int health) {
//        this.health = health;
//    }
//}
//package com.badlogic.mygame;
//
//import com.badlogic.gdx.Gdx;
//        import com.badlogic.gdx.graphics.Texture;
//        import com.badlogic.gdx.graphics.GL20;
