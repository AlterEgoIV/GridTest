package com.gridtest;

import com.badlogic.gdx.Input;

import java.util.List;

/**
 * Created by Carl on 23/05/2019.
 */
public class UnitController
{
    public List<Unit> units;
    public Unit currentUnit;
    public float tileWidth, tileHeight;
    public InputState inputState;

    public UnitController(List<Unit> units, float tileWidth, float tileHeight, InputState inputState)
    {
        this.units = units;
        currentUnit = units.get(0);
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.inputState = inputState;
    }

    public void update()
    {
        if(inputState.isDown(Input.Keys.UP))
        {
            currentUnit.position.y += tileHeight;
        }

        if(inputState.isDown(Input.Keys.DOWN))
        {
            currentUnit.position.y -= tileHeight;
        }

        if(inputState.isDown(Input.Keys.LEFT))
        {
            currentUnit.position.x -= tileWidth;
        }

        if(inputState.isDown(Input.Keys.RIGHT))
        {
            currentUnit.position.x += tileWidth;
        }
    }

    public void moveUnitUp()
    {
        currentUnit.position.y += tileHeight;
    }

    public void moveUnitDown()
    {
        currentUnit.position.y -= tileHeight;
    }

    public void moveUnitLeft()
    {
        currentUnit.position.x -= tileWidth;
    }

    public void moveUnitRight()
    {
        currentUnit.position.x += tileWidth;
    }
}
