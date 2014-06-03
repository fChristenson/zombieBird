package se.fidde.zombiebird.desktop;

import se.fidde.zombiebird.ZombieBirdGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "zombieBird";
        config.width = 272;
        config.height = 408;

        new LwjglApplication(new ZombieBirdGame(), config);
    }
}
