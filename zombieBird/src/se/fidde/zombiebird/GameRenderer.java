package se.fidde.zombiebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    public GameRenderer(GameWorld world) {
        this.world = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, 204);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);

        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
        shapeRenderer.rect(world.getRectangle().x, world.getRectangle().y,
                world.getRectangle().width, world.getRectangle().height);

        shapeRenderer.end();
        shapeRenderer.begin(ShapeType.Line);

        shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
        shapeRenderer.rect(world.getRectangle().x, world.getRectangle().y,
                world.getRectangle().getWidth(), world.getRectangle()
                        .getHeight());

        shapeRenderer.end();
    }

}
