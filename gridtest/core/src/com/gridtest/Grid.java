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
    public List<Unit> units;
    public Unit activeUnit;
    public GridCursor gridCursor;
    public UnitController unitController;
    public int columns, rows;
    public float tileWidth, tileHeight;
    public Tile[][] tiles;
    public List<Tile> activeTiles;

    public Grid(int columns, int rows, float tileWidth, float tileHeight, InputState inputState)
    {
        this.units = new ArrayList<Unit>();
        this.columns = columns;
        this.rows = rows;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tiles = new Tile[columns][rows];
        this.activeTiles = new ArrayList<Tile>();

        for(int i = 0; i < columns; ++i)
        {
            for(int j = 0; j < rows; ++j)
            {
                tiles[i][j] = createTile(new Vector2((i * tileWidth) + tileWidth / 2f, (j * tileHeight) + tileHeight / 2f), tileWidth, tileHeight, i, j, Color.SKY, Color.BLACK);
            }
        }

        units.add(createUnit(tiles[3][5].position.cpy(), tileWidth, tileHeight, Color.SCARLET, Color.CLEAR, tiles[3][5], 2));
        activeUnit = units.get(0);

        gridCursor = createGridCursor(tiles[0][0].position.cpy(), tileWidth, tileHeight, Color.CLEAR, Color.CLEAR);

        unitController = new UnitController(units, activeTiles, tileWidth, tileHeight, inputState);

        setActiveTiles();

        for(Tile tile : activeTiles)
        {
            tile.shape.fillColor = Color.BLUE;
        }
    }

    public void update()
    {
        //setActiveTiles();
        unitController.update();

//        for(Tile tile : activeTiles)
//        {
//            tile.shape.fillColor = Color.BLUE;
//        }
    }

    private void setActiveTiles()
    {
        Tile startTile = activeUnit.startTile;
        int movement = activeUnit.movement;
        int horizontalTiles = 1;
        activeTiles.clear();

        for(int i = movement; i >= 0; --i)
        {
            for(int j = -horizontalTiles / 2; j <= horizontalTiles / 2; ++j)
            {
                if(i > 0)
                {
                    if(!isOutOfBounds(startTile.column + j, startTile.row + i)) activeTiles.add(tiles[startTile.column + j][startTile.row + i]);
                    if(!isOutOfBounds(startTile.column + j, startTile.row - i)) activeTiles.add(tiles[startTile.column + j][startTile.row - i]);
                }
                else
                {
                    if(!isOutOfBounds(startTile.column + j, startTile.row)) activeTiles.add(tiles[startTile.column + j][startTile.row]);
                }
            }

            horizontalTiles += 2;
        }
    }

    private boolean isOutOfBounds(int column, int row)
    {
        return column < 0 || column >= columns || row < 0 || row >= rows;
    }

    private Tile createTile(Vector2 position, float width, float height, int i, int j, Color fillColor, Color outlineColor)
    {
        return new Tile(position, width, height, i, j, new Shape(position, width, height, ShapeName.RECT, fillColor, outlineColor));
    }

    private Unit createUnit(Vector2 position, float width, float height, Color fillColor, Color outlineColor, Tile startTile, int movement)
    {
        return new Unit(position, width, height, new Shape(position, width, height, ShapeName.ELLIPSE, fillColor, outlineColor), startTile, movement);
    }

    private GridCursor createGridCursor(Vector2 position, float width, float height, Color fillColor, Color outlineColor)
    {
        return new GridCursor(position, width, height, new Shape(position, width, height, ShapeName.RECT, fillColor, outlineColor));
    }
}
