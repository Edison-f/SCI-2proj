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
	float cleanliness;
	
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

	private void updateCleanliness() {
		cleanliness = 0;
		for (Trash t : trash) {
			cleanliness += t.getCompletion() / 100.0;
		}
		cleanliness /= TRASH_COUNT;
	}

	private void renderBackground() {
		// Clean water (0.0f, 0.412f, 0.58f)
		// Dirty water (0.568f, 0.482f, 0.309f)
		updateCleanliness();
		float red = 0.568f * (cleanliness) + 0.0f * (1 - cleanliness) ;
		float green = 0.482f * (cleanliness) + 0.412f * (1 - cleanliness) ;
		float blue = 0.309f * (cleanliness) + 0.58f * (1 - cleanliness) ;
		ScreenUtils.clear(red, green, blue, 1);
	}

	@Override
	public void render () {
		periodic();
		renderBackground();
//		ScreenUtils.clear(0.0f, 0.412f, 0.58f, 1);
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
