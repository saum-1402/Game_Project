//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.Body;
//import com.badlogic.gdx.physics.box2d.BodyDef;
//import com.badlogic.gdx.physics.box2d.ChainShape;
//import com.badlogic.gdx.physics.box2d.FixtureDef;
//import com.badlogic.gdx.physics.box2d.World;
//
//public class MapRenderer {
//    private int[][] map;
//    private int width;
//    private int height;
//    private Random random;
//    private Texture texture;
//    private World world;
//
//    // Constructor to initialize the map renderer
//    public MapRenderer(Texture texture, World world, int width, int height) {
//        this.texture = texture;
//        this.world = world;
//        this.width = width;
//        this.height = height;
//        this.map = new int[width][height];
//        this.random = new Random();
//    }
//
//    // Method to generate a new map
//    public void generateMap() {
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                map[i][j] = random.nextInt(2);
//
//                // If the square is not passable, create a Box2D body to represent it
//                if (map[i][j] == 0) {
//                    BodyDef bodyDef = new BodyDef();
//                    bodyDef.type = BodyDef.BodyType.StaticBody;
//                    bodyDef.position.set(i + 0.5f, j + 0.5f);
//
//                    Body body = world.createBody(bodyDef);
//
//                    ChainShape shape = new ChainShape();
//                    Vector2[] vertices = new Vector2[5];
//                    vertices[0] = new Vector2(-0.5f, -0.5f);
//                    vertices[1] = new Vector2(0.5f, -0.5f);
//                    vertices[2] = new Vector2(0.5f, 0.5f);
//                    vertices[3] = new Vector2(-0.5f, 0.5f);
//                    vertices[4] = new Vector2(-0.5f, -0.5f);
//                    shape.createChain(vertices);
//
//                    FixtureDef fixtureDef = new FixtureDef();
//                    fixtureDef.shape = shape;
//                    fixtureDef.density = 1.0f;
//                    fixtureDef.friction = 0.0f;
//                    fixtureDef.restitution = 0.0f;
//
//                    body.createFixture(fixtureDef);
//
//                    shape.dispose();
//                }
//            }
//        }
//    }
//
//// Method to draw the map on the screen
