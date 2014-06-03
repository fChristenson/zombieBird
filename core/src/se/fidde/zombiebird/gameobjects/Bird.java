package se.fidde.zombiebird.gameobjects;

import se.fidde.zombiebird.helpers.AssetLoader;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Bird {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private boolean isAlive;

    private float rotation;
    private int width;
    private int height;
    private Circle boundingCircle;

    public Bird(int width, int height, float x, float y) {
        this.width = width;
        this.height = height;

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
        isAlive = true;
    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y > 200) {
            velocity.y = 200;
        }

        // check for ceiling
        if (position.y < -13) {
            position.y = -13;
            velocity.y = 0;
        }
        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        // counterclockwise
        if (velocity.y < 0) {
            rotation -= 600 * delta;

            if (rotation < -20) {
                rotation = -20;
            }
        }

        // clockwise
        if (isFalling() || !isAlive) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }
        }
    }

    public boolean shouldNotFlap() {
        return velocity.y > 70 || !isAlive;
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public void onClick() {
        if (isAlive) {
            AssetLoader.flap.play();
            velocity.y = -140;
        }
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getRotation() {
        return rotation;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        velocity.y = 0;
        isAlive = false;
    }

    public void decelerate() {
        acceleration.y = 0;
    }

    public void restart(int yPos) {
        rotation = 0;
        velocity.y = 0;
        velocity.x = 0;
        position.y = yPos;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
    }

}