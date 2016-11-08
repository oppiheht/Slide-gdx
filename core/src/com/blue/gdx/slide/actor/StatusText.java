package com.blue.gdx.slide.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.blue.gdx.slide.ui.SlideAssetManager;


public class StatusText extends Actor {

    protected BitmapFont font;
    protected String text = "";

    public StatusText(AssetManager assetManager) {
        font = assetManager.get(SlideAssetManager.FONTFILE, BitmapFont.class);
        font.getData().setScale(Gdx.graphics.getDensity());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, text, getX(), getY());
    }

    public void setText(String text) {
        this.text = text;
    }
}
