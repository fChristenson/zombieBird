package se.fidde.zombiebird.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

    protected Vector2 velocity;
    protected Vector2 position;
    protected int width;
    protected int height;
    private boolean isScrolledLeft;

    public Scrollable(float x, float y, float scrollSpeed, int width, int height) {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    public void reset(float newX) {
        position.x = newX;
        isScrolledLeft = false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getTailX() {
        return position.x + width;
    }

    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public void stop() {
        velocity.x = 0;
    }

    public void restart(float xPos, float scrollSpeed) {
        position.x = xPos;
        velocity.x = scrollSpeed;
    }
}