package se.fidde.zombiebird.gameobjects;

import static se.fidde.zombiebird.helpers.AssetLoader.coin;
import se.fidde.zombiebird.GameWorld;

/**
 * ScrollHandler handles {@link Scrollable} actors
 * 
 * @author fidde
 *
 */
public class ScrollHandler {
    public static final int SCROLL_SPEED = -59;
    public static final int PIPE_GAP = 49;

    private GameWorld world;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    public ScrollHandler(GameWorld world, float yPos) {
        this.world = world;
        frontGrass = new Grass(0, yPos, SCROLL_SPEED, 143, 11);
        backGrass = new Grass(frontGrass.getTailX(), yPos, SCROLL_SPEED, 143,
                11);

        pipe1 = new Pipe(210, 0, SCROLL_SPEED, 22, 60, yPos);
        pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, SCROLL_SPEED, 22, 70,
                yPos);
        pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 0, SCROLL_SPEED, 22, 60,
                yPos);
    }

    /**
     * Calls update on all {@link Scrollable} actors
     * 
     * @param delta
     *            delta between frames
     */
    public void update(float delta) {
        frontGrass.update(delta);
        backGrass.update(delta);

        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);

        if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe3.getTailX() + PIPE_GAP);

        } else if (pipe2.isScrolledLeft()) {
            pipe2.reset(pipe1.getTailX() + PIPE_GAP);

        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_GAP);
        }

        if (frontGrass.isScrolledLeft()) {
            frontGrass.reset(backGrass.getTailX());

        } else if (backGrass.isScrolledLeft()) {
            backGrass.reset(frontGrass.getTailX());
        }
    }

    /**
     * Calls stop on all {@link Scrollable} actors
     */
    public void stop() {
        frontGrass.stop();
        backGrass.stop();
        pipe1.stop();
        pipe2.stop();
        pipe3.stop();
    }

    /**
     * Checks for collisions with {@link Bird}
     * 
     * @param bird
     * @return true if a collision is found.
     */
    public boolean collides(Bird bird) {
        if (!pipe1.isScored()
                && pipe1.getX() + (pipe1.getWidth() / 2) < bird.getX()
                        + bird.getWidth()) {

            world.addScore(1);
            pipe1.setScored(true);
            coin.play();

        } else if (!pipe2.isScored()
                && pipe2.getX() + (pipe2.getWidth() / 2) < bird.getX()
                        + bird.getWidth()) {

            world.addScore(1);
            pipe2.setScored(true);
            coin.play();

        } else if (!pipe3.isScored()
                && pipe3.getX() + (pipe3.getWidth() / 2) < bird.getX()
                        + bird.getWidth()) {

            world.addScore(1);
            pipe3.setScored(true);
            coin.play();
        }

        return pipe1.collides(bird) || pipe2.collides(bird)
                || pipe3.collides(bird);
    }

    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
    }

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }

    /**
     * Calls restart on all {@link Scrollable} actors
     */
    public void restart() {
        frontGrass.restart(0, SCROLL_SPEED);
        backGrass.restart(frontGrass.getTailX(), SCROLL_SPEED);

        pipe1.restart(210, SCROLL_SPEED);
        pipe2.restart(pipe1.getTailX() + PIPE_GAP, SCROLL_SPEED);
        pipe3.restart(pipe2.getTailX() + PIPE_GAP, SCROLL_SPEED);
    }
}
