package com.boat.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	Stage stage;


	Boat player;
	ArrayList<Trash> trash;
	UI ui;

	final int TRASH_COUNT = 10;
	
	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		player = new Boat(0, 0, shapeRenderer, batch);
		trash = new ArrayList<>();
//		trash.add(new Trash(200, 200, shapeRenderer, batch));
		generateTrash();
		ui = new UI(player, trash, stage);
	}

	public void generateTrash() {
		for (int i = 0; i < TRASH_COUNT; i++) {
			trash.add(new Trash((int) (Math.random() * 800), (int) (Math.random() * 600), shapeRenderer, batch));
		}
	}

	@Override
	public void render () {
		periodic();
		ScreenUtils.clear(0.0f, 0.412f, 0.58f, 1);
		batch.begin();
		for (Trash t : trash) {
			t.render();
		}
		player.render();
		ui.render();
		batch.end();
	}

	public void periodic() {
		ui.processInput();
		ui.logic();
	}

	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();
		shapeRenderer.dispose();
	}
}
