package com.boat.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Trash {
    private int x;
    private int y;
    private int width;
    private int height;
    private int completion;
    private boolean collected;
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    float red;
    float green;
    float blue;


    public Trash(int x, int y, ShapeRenderer shapeRenderer, SpriteBatch batch) {
        this.x = x;
        this.y = y;
        completion = 100;
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;
        width = 100;
        height = 100;
    }

    public void render() {
        if(!collected) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            adjustColor();
            shapeRenderer.setColor(red, green, blue, 1);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
        }
    }

    public void adjustColor() {
        red = 0.2f * (float) (completion + 50) / 50;
        green = 0.5f * (float) (completion + 50) / 50;
        blue = 0.3f * (float) (completion + 50) / 50;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCompletion() {
        return completion;
    }

    public void collect(int n) {
        completion -= n;
        if(completion < 1) {
            completion = 1;
            collected = true;
        }
    }

    public void reset() {
        completion = 100;
    }
}
