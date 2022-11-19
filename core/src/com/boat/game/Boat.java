package com.boat.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Boat {
    private int x;
    private int y;
    private int width;
    private int height;
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;

    public Boat(int x, int y, ShapeRenderer shapeRenderer, SpriteBatch batch) {
        this.x = x;
        this.y = y;
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;
    }

    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.0f, 0.0f, 0.0f, 1);
        shapeRenderer.rect(x, y, 50, 50);
        shapeRenderer.end();
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

    public void moveLeft(int n) {
        x -= n;
    }

    public void moveRight(int n) {
        x += n;
    }

    public void moveUp(int n) {
        y += n;
    }

    public void moveDown(int n) {
        y -= n;
    }
}
