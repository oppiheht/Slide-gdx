package com.blue.gdx.slide.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public abstract class SlideActor extends Image {

    protected int worldX = 0;
    protected int worldY = 0;
    protected int size = 0;

    public SlideActor(int size) {
        setDefaultSizeAndOrigin(size);
    }

    public SlideActor(Texture t, int size) {
        super(t);
        setDefaultSizeAndOrigin(size);
    }

    private void setDefaultSizeAndOrigin(int size) {
        this.size = size;
        setSize(size, size);
        setOrigin(size/2, size/2);
    }

    protected abstract float getAnimationDuration();

    @Override
    public void setPosition(float x, float y) {
       addAction(Actions.moveTo(x * size, y * size, getAnimationDuration(), Interpolation.pow2));
       worldX = (int) x;
       worldY = (int) y;
    }

    public void setDrawable(Texture t) {
        setDrawable(new TextureRegionDrawable(new TextureRegion(t)));
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }


}
