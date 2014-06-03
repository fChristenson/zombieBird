package se.fidde.zombiebird.gameobjects;

import static com.badlogic.gdx.math.Intersector.overlaps;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class holding data for pipe actor
 * 
 * @author fidde
 *
 */
public class Pipe extends Scrollable {

    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;

    private Rectangle skullUp, skullDown, barUp, barDown;
    private float grassSurfacePos;
    private Random random;
    private boolean isScored;

    public Pipe(float x, float y, float scrollSpeed, int width, int height,
            float grassSurfacePos) {
        super(x, y, scrollSpeed, width, height);

        random = new Random();
        skullUp = new Rectangle();
        skullDown = new Rectangle();
        barUp = new Rectangle();
        barDown = new Rectangle();

        this.grassSurfacePos = grassSurfacePos;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        // The set() method allows you to set the top left corner's x, y
        // coordinates,
        // along with the width and height of the rectangle

        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
                grassSurfacePos - (position.y + height + VERTICAL_GAP));

        // Our skull width is 24. The bar is only 22 pixels wide. So the skull
        // must be shifted by 1 pixel to the left (so that the skull is centered
        // with respect to its bar).

        // This shift is equivalent to: (SKULL_WIDTH - width) / 2
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
                - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        height = random.nextInt(90) + 15;
        isScored = false;
    }

    /**
     * Checks for collisions
     * 
     * @param bird
     * @return true if a collision is found
     */
    public boolean collides(Bird bird) {
        if (position.x < bird.getX() + bird.getWidth()) {
            Circle circle = bird.getBoundingCircle();
            return overlaps(circle, barUp) || overlaps(circle, barDown)
                    || overlaps(circle, skullUp) || overlaps(circle, skullDown);
        }
        return false;
    }

    public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }

    public float getGrassSurfacePos() {
        return grassSurfacePos;
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean scored) {
        isScored = scored;
    }

    @Override
    public void restart(float xPos, float scrollSpeed) {
        velocity.x = scrollSpeed;
        reset(xPos);

    }
}
