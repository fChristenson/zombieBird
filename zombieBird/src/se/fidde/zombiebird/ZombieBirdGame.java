package se.fidde.zombiebird;

import se.fidde.zombiebird.screens.GameScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class ZombieBirdGame extends Game implements ApplicationListener {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
