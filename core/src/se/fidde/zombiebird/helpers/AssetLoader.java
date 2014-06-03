package se.fidde.zombiebird.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    private static final String HIGHSCORE = "highscore";
    public static Preferences prefs;
    public static Texture texture;
    public static Animation birdAnimation;
    public static BitmapFont font, shadow;

    public static TextureRegion bg, grass;
    public static TextureRegion birdDown, birdUp, bird;
    public static TextureRegion skullUp, skullDown, bar;

    public static Sound dead;
    public static Sound coin;
    public static Sound flap;

    public static int getHighscore() {
        prefs = Gdx.app.getPreferences(HIGHSCORE);
        return prefs.getInteger(HIGHSCORE);
    }

    public static void setHighscore(int newScore) {
        prefs = Gdx.app.getPreferences(HIGHSCORE);
        prefs.putInteger(HIGHSCORE, newScore);
        prefs.flush();
    }

    public static void load() {
        prefs = Gdx.app.getPreferences(HIGHSCORE);
        if (!prefs.contains(HIGHSCORE)) {
            prefs.putInteger(HIGHSCORE, 0);
        }

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

        TextureRegion[] birds = { birdDown, bird, birdUp };
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);

        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(0.25f, -0.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(0.25f, -0.25f);
    }

    public static void dispose() {
        texture.dispose();

        dead.dispose();
        coin.dispose();
        flap.dispose();

        font.dispose();
        shadow.dispose();
    }
}
