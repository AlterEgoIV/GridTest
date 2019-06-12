package com.gridtest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Created by Carl on 23/05/2019.
 */
public class UnitController
{
    public List<Unit> units;
    public Unit unit;
    public float distancePerFrame, currentDistance, totalDistance;
    public InputState inputState;
    public List<Tile> activeTiles;
    public Vector2 direction;
    public boolean isUnitMoving;
    public Tile targetTile;

    public UnitController(List<Unit> units, List<Tile> activeTiles, InputState inputState)
    {
        this.units = units;
        this.activeTiles = activeTiles;
        this.unit = units.get(0);
        this.inputState = inputState;
        this.direction = new Vector2();
        this.isUnitMoving = false;
        this.distancePerFrame = 0f;
        this.targetTile = unit.currentTile;
        this.currentDistance = 0f;
        this.totalDistance = 0f;
    }

    public void update()
    {
        if(!isUnitMoving)
        {
            handleInput();
        }

        if(isUnitMoving)
        {
            moveUnit();
        }
    }

    private void handleInput()
    {
        if(inputState.isDown(Input.Keys.UP)) setTargetTile(0, 1);
        if(inputState.isDown(Input.Keys.DOWN)) setTargetTile(0, -1);
        if(inputState.isDown(Input.Keys.LEFT)) setTargetTile(-1, 0);
        if(inputState.isDown(Input.Keys.RIGHT)) setTargetTile(1, 0);
        if(inputState.isDown(Input.Keys.A)) setStartTile();
    }

    private void setTargetTile(int columnOffset, int rowOffset)
    {
        for(Tile tile : activeTiles)
        {
            if(tile.column == unit.currentTile.column + columnOffset && tile.row == unit.currentTile.row + rowOffset)
            {
                isUnitMoving = true;
                targetTile = tile;
                direction.set(targetTile.position.x - unit.currentTile.position.x, targetTile.position.y - unit.currentTile.position.y);
                totalDistance = direction.len();
                distancePerFrame = (direction.len() / unit.moveTime) * Gdx.graphics.getDeltaTime();
                direction.nor();
            }
        }
    }

    private void moveUnit()
    {
        unit.position.add(direction.x * distancePerFrame, direction.y * distancePerFrame);
        currentDistance += distancePerFrame;

        if(currentDistance >= totalDistance)
        {
            isUnitMoving = false;
            unit.currentTile = targetTile;
            unit.position.set(targetTile.position);
            currentDistance = 0;
        }
    }

    private void setStartTile()
    {
        unit.startTile = unit.currentTile;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }
}
