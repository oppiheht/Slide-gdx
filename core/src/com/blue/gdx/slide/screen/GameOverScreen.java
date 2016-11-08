package com.blue.gdx.slide.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.actor.Background;
import com.blue.gdx.slide.actor.GameOverText;
import com.blue.gdx.slide.actor.StatusText;


public class GameOverScreen extends ScreenAdapter {

    private final SlideGame game;
    private Stage stage;
    private String text;


    public GameOverScreen(SlideGame game, String text) {
        this.game = game;
        this.text = text;
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        GameOverText gameOverText = new GameOverText(game.getAssetManager(), text);
        gameOverText.setPosition(Gdx.graphics.getWidth() * .05f, Gdx.graphics.getHeight() / 2);

        stage.addActor(new Background(game.getAssetManager()));
        stage.addActor(gameOverText);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
        queryBackButtonPressed();
    }

    protected void queryBackButtonPressed() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            game.setScreen(new StartScreen(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
