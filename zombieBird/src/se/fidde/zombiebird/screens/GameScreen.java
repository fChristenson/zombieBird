package se.fidde.zombiebird.screens;

import se.fidde.zombiebird.GameRenderer;
import se.fidde.zombiebird.GameWorld;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer render;

    public GameScreen() {
        world = new GameWorld();
        render = new GameRenderer(world);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        render.render();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
