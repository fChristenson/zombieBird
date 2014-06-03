package se.fidde.zombiebird;

import se.fidde.zombiebird.gameobjects.Bird;
import se.fidde.zombiebird.gameobjects.GameState;
import se.fidde.zombiebird.gameobjects.ScrollHandler;
import se.fidde.zombiebird.helpers.AssetLoader;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {

    private Bird bird;
    private ScrollHandler scrollHandler;
    private Rectangle ground;
    private int midPointY;
    private int score;
    private GameState state;

    public GameWorld(int midPointY) {
        this.midPointY = midPointY;
        state = GameState.READY;
        bird = new Bird(17, 12, 33, midPointY - 5);
        scrollHandler = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {
        switch (state) {

        case READY:
            stateReadyUpdate(delta);
            break;

        case RUNNING:
            stateRunningUpdate(delta);
            break;

        default:
            break;
        }
    }

    private void stateReadyUpdate(float delta) {

    }

    private void stateRunningUpdate(float delta) {
        if (delta > 0.15f) {
            delta = 0.15f;
        }
        bird.update(delta);
        scrollHandler.update(delta);

        if (bird.isAlive() && scrollHandler.collides(bird)) {
            scrollHandler.stop();
            bird.die();
            AssetLoader.dead.play();

        } else if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scrollHandler.stop();
            bird.die();
            bird.decelerate();
            state = GameState.OVER;

            if (score > AssetLoader.getHighscore()) {
                AssetLoader.setHighscore(score);
                state = GameState.HIGHSCORE;
            }
        }
    }

    public boolean isHighscore() {
        return state == GameState.HIGHSCORE;
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScroller() {
        return scrollHandler;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }

    public boolean isReady() {
        return state == GameState.READY;
    }

    public boolean isGameOver() {
        return state == GameState.OVER;
    }

    public void start() {
        state = GameState.RUNNING;
    }

    public void restart() {
        state = GameState.READY;
        score = 0;
        bird.restart(midPointY - 5);
        scrollHandler.restart();
    }
}