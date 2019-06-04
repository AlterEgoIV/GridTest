package com.gridtest;

import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carl on 23/05/2019.
 */
public class UnitController
{
    public List<Unit> units;
    public Unit unit;
    public float tileWidth, tileHeight;
    public InputState inputState;
    public List<Tile> activeTiles;

    public UnitController(List<Unit> units, List<Tile> activeTiles, float tileWidth, float tileHeight, InputState inputState)
    {
        this.units = units;
        this.activeTiles = activeTiles;
        unit = units.get(0);
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.inputState = inputState;
    }

    public void update()
    {
        handleInput();
    }

    private void handleInput()
    {
        if(inputState.isDown(Input.Keys.UP)) moveUnit(0, 1);
        if(inputState.isDown(Input.Keys.DOWN)) moveUnit(0, -1);
        if(inputState.isDown(Input.Keys.LEFT)) moveUnit(-1, 0);
        if(inputState.isDown(Input.Keys.RIGHT)) moveUnit(1, 0);
    }

    private void moveUnit(int columnOffset, int rowOffset)
    {
        for(Tile tile : activeTiles)
        {
            if(tile.column == unit.currentTile.column + columnOffset && tile.row == unit.currentTile.row + rowOffset)
            {
                unit.position.set(tile.position);
                unit.currentTile = tile;
            }
        }
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }
}
