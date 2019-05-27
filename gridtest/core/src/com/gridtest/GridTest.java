package com.gridtest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GridTest extends ApplicationAdapter implements InputProcessor
{
	private InputState inputState;
	private Grid grid;
	private Renderer renderer;

	@Override
	public void create()
	{
		Gdx.input.setInputProcessor(this);

		inputState = new InputState();
		grid = new Grid(10, 10, Gdx.graphics.getWidth() / 10f, Gdx.graphics.getHeight() / 10f, inputState);
		renderer = new Renderer();

		for(Tile tile : grid.tiles)
		{
			renderer.addShape(tile.shape);
		}

		for(Unit unit : grid.units)
		{
			renderer.addShape(unit.shape);
		}

		renderer.addShape(grid.gridCursor.shape);
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		grid.update();
		renderer.render();
	}
	
	@Override
	public void dispose()
	{
		renderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode)
	{
		inputState.keyDown(keycode);

		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		inputState.keyUp(keycode);

		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}
}
