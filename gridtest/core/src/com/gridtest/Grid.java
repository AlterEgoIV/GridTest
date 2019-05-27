package com.gridtest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carl on 06/05/2019.
 */
public class Grid
{
    public List<Tile> tiles;
    public List<Unit> units;
    public GridCursor gridCursor;
    public UnitController unitController;
    private int columns, rows;
    private float tileWidth, tileHeight;

    public Grid(int columns, int rows, float tileWidth, float tileHeight, InputState inputState)
    {
        this.tiles = new ArrayList<Tile>();
        this.units = new ArrayList<Unit>();
        this.columns = columns;
        this.rows = rows;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        for(int i = 0; i < columns; ++i)
        {
            for(int j = 0; j < rows; ++j)
            {
                tiles.add(createTile(new Vector2((i * tileWidth) + tileWidth / 2f, (j * tileHeight) + tileHeight / 2f), tileWidth, tileHeight, i, j, Color.SKY, Color.CLEAR));
            }
        }

        units.add(createUnit(tiles.get(10).position.cpy(), tileWidth, tileHeight, Color.SCARLET, Color.CLEAR));

        gridCursor = createGridCursor(tiles.get(10).position.cpy(), tileWidth, tileHeight, Color.CLEAR, Color.CLEAR);

        unitController = new UnitController(units, tileWidth, tileHeight, inputState);
    }

    public void update()
    {
        unitController.update();
    }

    private Tile createTile(Vector2 position, float width, float height, int i, int j, Color fillColor, Color outlineColor)
    {
        return new Tile(position, width, height, i, j, new Shape(position, width, height, ShapeName.RECT, fillColor, outlineColor));
    }

    private Unit createUnit(Vector2 position, float width, float height, Color fillColor, Color outlineColor)
    {
        return new Unit(position, width, height, new Shape(position, width, height, ShapeName.ELLIPSE, fillColor, outlineColor));
    }

    private GridCursor createGridCursor(Vector2 position, float width, float height, Color fillColor, Color outlineColor)
    {
        return new GridCursor(position, width, height, new Shape(position, width, height, ShapeName.RECT, fillColor, outlineColor));
    }
}
