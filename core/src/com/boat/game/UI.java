package com.boat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

public class UI {

    Boat player;
    ArrayList<Trash> trash;
    Stage stage;
    Label.LabelStyle debugLabelStyle;
    Label debugLabel;
    BitmapFont font;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter params;

    public UI (Boat player, ArrayList<Trash> trash, Stage stage) {
        this.player = player;
        this.trash = trash;
        this.stage = stage;
        debugLabelStyle = new Label.LabelStyle();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("gadaj_font.otf"));
        params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        params.borderWidth = 1;
        params.borderColor = Color.BLACK;
        params.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
        params.magFilter = Texture.TextureFilter.Nearest;
        params.minFilter = Texture.TextureFilter.Nearest;
        params.genMipMaps = true;
        params.size = 20;

        BitmapFont font = generator.generateFont(params);
        debugLabelStyle.font = font;
        debugLabelStyle.fontColor = Color.WHITE;

        debugLabel = new Label("Test", debugLabelStyle);
        debugLabel.setAlignment(Align.topLeft);
        debugLabel.setPosition(0, 600);

        stage.addActor(debugLabel);
    }

    public void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft(5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight(5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp(5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown(5);
        }
    }

    public void logic() {
        collision();
    }

    private void text() {
        boolean xCollision = player.getX() + player.getWidth() > trash.get(0).getX() && player.getX() < trash.get(0).getX() + trash.get(0).getWidth();
        boolean yCollision = player.getY() + player.getHeight() > trash.get(0).getY() && player.getY() < trash.get(0).getY() + trash.get(0).getHeight();
        String debugString = "Player X: " + player.getX() + " Y: " + player.getY()
                + " Trash X: " + trash.get(0).getX() + " Y: " + trash.get(0).getY()
                + "\nCollision X: " + xCollision + " Collision Y: " + yCollision;
        debugLabel.setText(debugString);

        stage.act();
        stage.draw();
    }

    public void dispose() {

    }

    public void render() {
        text();
    }

    private void collision() {
        for (Trash t : trash) {
            boolean xCollision = player.getX() + player.getWidth() > t.getX() && player.getX() < t.getX() + t.getWidth();
            if(xCollision
                    && player.getY() + player.getHeight() > t.getY() && player.getY() < t.getY() + t.getHeight()) {
                t.collect(1);
            }
        }
    }
}
