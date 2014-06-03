package se.fidde.zombiebird;

import se.fidde.zombiebird.gameobjects.Bird;
import se.fidde.zombiebird.gameobjects.Grass;
import se.fidde.zombiebird.gameobjects.Pipe;
import se.fidde.zombiebird.gameobjects.ScrollHandler;
import se.fidde.zombiebird.helpers.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * Class in charge of rendering sprites and assets
 * 
 * @author fidde
 *
 */
public class GameRenderer {

    // Game objects
    private Bird bird;
    private ScrollHandler scroller;
    private Pipe pipe1, pipe2, pipe3;
    private Grass frontGrass, backGrass;

    // assets
    private Texture texture;
    private TextureRegion bg, grass;
    private Animation birdAnimation;
    private TextureRegion birdDown, birdUp, birdMid;
    private TextureRegion skullUp, skullDown, bar;

    private GameWorld world;
    private int gameHeight;
    private int midPointY;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    /**
     * Default Constructor
     * 
     * @param world
     *            {@link GameWorld}
     * @param gameHeight
     *            height of the screen
     * @param midPointY
     *            midpoint on the y axel
     */
    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        this.world = world;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, gameHeight);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }

    public void initGameObjects() {
        bird = world.getBird();
        scroller = world.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
    }

    public void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        birdAnimation = AssetLoader.birdAnimation;
        birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }

    private void drawGrass() {
        batch.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());

        batch.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        // draw skull set 1
        batch.draw(skullUp, pipe1.getX() - 1, pipe1.getY() + pipe1.getHeight()
                - 14, 24, 14);
        batch.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        // draw skullset 2
        batch.draw(skullUp, pipe2.getX() - 1, pipe2.getY() + pipe2.getHeight()
                - 14, 24, 14);
        batch.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        // draw skullset 3
        batch.draw(skullUp, pipe3.getX() - 1, pipe3.getY() + pipe3.getHeight()
                - 14, 24, 14);
        batch.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        // pipeset 1
        batch.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batch.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        // pipeset 2
        batch.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batch.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        // pipeset 3
        batch.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batch.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();
        batch.begin();
        batch.disableBlending();

        batch.draw(bg, 0, midPointY + 23, 136, 43);
        drawGrass();
        drawPipes();

        batch.enableBlending();
        drawSkulls();

        // draw birdanimation
        if (bird.shouldNotFlap()) {
            batch.draw(birdMid, bird.getX(), bird.getY(),
                    bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());

        } else {
            batch.draw(birdAnimation.getKeyFrame(runTime), bird.getX(),
                    bird.getY(), bird.getWidth() / 2.0f,
                    bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(),
                    1, 1, bird.getRotation());
        }

        // draw screen messages
        if (world.isReady()) {
            AssetLoader.shadow.draw(batch, "Tap screen", (136 / 2) - (42), 76);
            AssetLoader.font
                    .draw(batch, "Tap screen", (136 / 2) - (42 - 1), 75);
        } else {

            if (world.isGameOver() || world.isHighscore()) {
                AssetLoader.shadow.draw(batch, "Game Over", 25, 56);
                AssetLoader.font.draw(batch, "Game Over", 24, 55);

                AssetLoader.shadow.draw(batch, "High Score:", 23, 106);
                AssetLoader.font.draw(batch, "High Score:", 22, 105);

                String highscore = String.valueOf(AssetLoader.getHighscore());

                AssetLoader.shadow.draw(batch, highscore, (136 / 2)
                        - (3 * highscore.length()), 128);
                AssetLoader.font.draw(batch, highscore, (136 / 2)
                        - (3 * highscore.length() - 1), 127);
            }

            // draw score
            String score = String.valueOf(world.getScore());

            AssetLoader.shadow.draw(batch, score,
                    (136 / 2) - (3 * score.length()), 12);
            AssetLoader.font.draw(batch, score, (136 / 2)
                    - (3 * score.length() - 1), 11);
        }
        batch.end();
    }
}