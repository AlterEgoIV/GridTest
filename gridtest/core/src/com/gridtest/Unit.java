package com.gridtest;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Carl on 06/05/2019.
 */
public class Unit extends GameObject
{
    public Tile startTile, currentTile;
    public int movement;
    public float moveTime;

    public Unit(Vector2 position, float width, float height, Shape shape, Tile startTile, int movement)
    {
        super(position, width, height, shape);
        this.startTile = startTile;
        this.currentTile = startTile;
        this.movement = movement;
        this.moveTime = .2f;
    }

    @Override
    public void update()
    {

    }
}
